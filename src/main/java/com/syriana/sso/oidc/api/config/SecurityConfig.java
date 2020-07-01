package com.syriana.sso.oidc.api.config;

import com.syriana.sso.oidc.api.service.authorization.SelfAuthenticationProvider;
import com.syriana.sso.oidc.api.service.authorization.SelfBcryptPswEncoder;
import com.syriana.sso.oidc.api.service.authorization.SelfUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collections;

/**
 * @author syriana.zh
 * @date 2020/06/22
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SelfUserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    SelfAuthenticationProvider selfAuthenticationProvider;
//    @Autowired
//    SelfBcryptPswEncoder selfBcryptPswEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义用户验证 注释掉该代码，
        // 就不会再注册DaoAuthenticationProvider，然后在自定义Provider里抛异常也能正确显示了
        // 实际上注册了自己的provider后，已经默认可以使用自定义的userDetailServiceImpl了，有空再研究DaoAuthenticationProvider
//        auth.userDetailsService(userDetailServiceImpl);
        auth.authenticationProvider(selfAuthenticationProvider);// 自定义用户校验器
//        auth.inMemoryAuthentication()
//                .withUser("hellxz")
//                .password(selfBcryptPswEncoder.encode("xyz"))
//                .authorities(Collections.emptyList());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //所有请求都需要通过认证
                .and()
                // Basic登录, 如果开启，调用oauth/authorize时，
                // 选择BASIC AUTH 填入用户账号密码，直接就能选择是否授权
                // 否则，就要手动登录
//                .httpBasic()
//                .and()
                .formLogin()
                .and()
                .csrf().disable(); //关跨域保护
    }
}
