package cn.sdadgz.blogbackend.config;

import cn.sdadgz.blogbackend.filter.JwtTokenAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2024/7/16 12:33:56
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final JwtTokenAuthorization jwtTokenAuthorization;

    /**
     * 密码明文加密方式配置
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/posts").permitAll()
                        .anyRequest().authenticated()
                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("")
//                        .permitAll()
//                )
                .rememberMe(Customizer.withDefaults())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .csrf(AbstractHttpConfigurer::disable);

        // 拦截器
        http.addFilterBefore(jwtTokenAuthorization, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
