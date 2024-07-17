package cn.sdadgz.blogbackend.util;

import cn.sdadgz.blogbackend.entity.AuthorizationUser;
import cn.sdadgz.blogbackend.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户工具栏
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2024/7/16 18:06:45
 */
public class UserUtil {

    public static User getUser() {
        AuthorizationUser principal = (AuthorizationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser();
    }

    public static Long getUserId() {
        return getUser().getUserId();
    }

}
