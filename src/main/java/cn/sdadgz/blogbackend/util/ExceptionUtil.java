package cn.sdadgz.blogbackend.util;

import cn.sdadgz.blogbackend.exception.CommonException;

/**
 * 异常工具
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2024/3/19 22:09:50
 */
public class ExceptionUtil {
    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param e         异常
     */
    public static void throwIf(boolean condition, CommonException e) {
        if (condition) {
            throw e;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static void throwIf(boolean condition, String errorCode, String message) {
        throwIf(condition, new CommonException(errorCode, message));
    }
}
