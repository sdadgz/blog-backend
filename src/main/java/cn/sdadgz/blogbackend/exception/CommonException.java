package cn.sdadgz.blogbackend.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用异常类
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2023/3/23 18:40:33
 */
@Getter
@Setter
public class CommonException extends RuntimeException {

    private String code;

    public CommonException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
