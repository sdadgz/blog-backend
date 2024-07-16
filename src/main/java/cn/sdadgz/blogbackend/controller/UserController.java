package cn.sdadgz.blogbackend.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.HMacJWTSigner;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import cn.sdadgz.blogbackend.common.Constants;
import cn.sdadgz.blogbackend.common.Result;
import cn.sdadgz.blogbackend.entity.AuthorizationUser;
import cn.sdadgz.blogbackend.entity.User;
import cn.sdadgz.blogbackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sdadgz
 * @since 2024-07-16
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        // 内部使用 UserDetailsService 查询用户，正确返回信息 错误返回空（大概）
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // 存入刚才返回信息的内部存储的 UserDetails
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        AuthorizationUser principal = (AuthorizationUser) authenticate.getPrincipal();
        System.out.println(principal);

        // 返回一个token，在 JwtAuthenticationTokenFilter 中根据这个token获取权限和信息
        Map<String, Object> map = BeanUtil.beanToMap(principal.getUser());
        map.put("exp", LocalDateTimeUtil.now().plusDays(1));
        String token = JWTUtil.createToken(map, JWTSignerUtil.hs256((principal.getPassword() +
                Constants.SALT)
                .getBytes(StandardCharsets.UTF_8)));

        return Result.success(Dict.parse(principal.getUser())
                .set("token", token));
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (Objects.isNull(user.getUsername()) || Objects.isNull(user.getPassword())) {
            return Result.error(Constants.CODE_400, "用户名或密码不能为空");
        }

        userService.save(user
                .setCreate(LocalDateTimeUtil.now())
                .setLastModified(LocalDateTimeUtil.now())
                .setPassword(passwordEncoder.encode(user.getPassword())));
        return Result.success();
    }

    @GetMapping("/test")
    public Result test() {
        return Result.success(SecurityContextHolder.getContext().getAuthentication());
    }
}
