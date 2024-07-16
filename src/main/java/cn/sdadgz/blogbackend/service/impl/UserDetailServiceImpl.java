package cn.sdadgz.blogbackend.service.impl;

import cn.sdadgz.blogbackend.common.Constants;
import cn.sdadgz.blogbackend.entity.AuthorizationUser;
import cn.sdadgz.blogbackend.entity.User;
import cn.sdadgz.blogbackend.exception.CommonException;
import cn.sdadgz.blogbackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2023/2/22 19:46:57
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
        if (Objects.isNull(user)) {
            throw new CommonException(Constants.CODE_400, "用户名或密码错误");
        }
        return new AuthorizationUser(user);
    }
}
