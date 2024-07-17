package cn.sdadgz.blogbackend.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import cn.sdadgz.blogbackend.common.Constants;
import cn.sdadgz.blogbackend.entity.AuthorizationUser;
import cn.sdadgz.blogbackend.entity.User;
import cn.sdadgz.blogbackend.exception.CommonException;
import cn.sdadgz.blogbackend.service.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * jwt
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2024/7/16 13:29:31
 */
@Component
@RequiredArgsConstructor
public class JwtTokenAuthorization  extends OncePerRequestFilter {

    private final IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 头
        String header = request.getHeader(Constants.TOKEN_HEADER);

        // 放行未认证
        if (Objects.isNull(header)) {
            doFilter(request, response, filterChain);
            return;
        }


        header = header.split(" ")[1];
        JWT token = JWTUtil.parseToken(header);
//        System.out.println(token);

        // 从服务器获取并验证token合法性
        long userId = Long.parseLong(token.getPayload(User.Fields.userId).toString());
        User user = userService.getById(userId);

        if (Objects.isNull(user)) {
            throw new CommonException(Constants.CODE_401, "用户不存在");
        }

        AuthorizationUser authorizationUser = new AuthorizationUser(user);

        if (!token.verify(JWTSignerUtil.hs256((authorizationUser.getPassword() + Constants.SALT).getBytes(StandardCharsets.UTF_8)))) {
            throw new CommonException(Constants.CODE_401, "token不合法");
        }

        long exp = Long.parseLong(token.getPayload("exp").toString());
//        System.out.println(exp - 2592000);
//        System.out.println(System.currentTimeMillis() / 1000);

        if (System.currentTimeMillis() / 1000 > exp) {
            throw new CommonException(Constants.CODE_401, "token过期");
        }


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authorizationUser, authorizationUser.getPassword(),/* 授权 */ authorizationUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        doFilter(request, response, filterChain);
    }
}
