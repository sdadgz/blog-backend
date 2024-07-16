package cn.sdadgz.blogbackend.exception;

import cn.sdadgz.blogbackend.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常接管类
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2023/3/23 18:42:04
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    // 常规异常
    @ExceptionHandler(CommonException.class)
    public Result handlerCommonException(CommonException e) {
        return Result.error(e.getCode(), "异常：" + e.getMessage());
    }

}
