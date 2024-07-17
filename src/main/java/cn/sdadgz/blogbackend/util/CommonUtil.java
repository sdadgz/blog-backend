package cn.sdadgz.blogbackend.util;

import cn.hutool.core.util.StrUtil;

import java.util.Collection;

/**
 * 通用工具类
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2024/7/17 09:32:23
 */
public class CommonUtil {

    /**
     * 判断对象是否为空
     * entity -> 判断空对象
     * String -> 判断空字符串和全空格
     * Collection -> 判断空集合
     *
     * @param obj 对象
     * @return 是否为空
     */
    public static boolean isNull(Object obj) {
        if (obj instanceof String) {
            return StrUtil.isBlank((String) obj);
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        return obj == null;
    }


    /**
     * 如果没有值采用默认值，isNull方法判断
     * entity -> 判断空对象
     * String -> 判断空字符串和全空格
     * Collection -> 判断空集合
     *
     * @param value        判断是否为空的值
     * @param defaultValue 为空时采用的默认值
     * @return js语法中的 value || defaultValue
     */
    public static <VALUE> VALUE thisOrDefault(VALUE value, VALUE defaultValue) {
        return !isNull(value) ? value : defaultValue;
    }


}
