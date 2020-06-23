package com.syriana.sso.oidc.api.config;

import com.syriana.sso.oidc.api.service.authorization.SelfAuthenticationProvider;
import com.syriana.sso.oidc.api.service.authorization.SelfBcryptPswEncoder;
import com.syriana.sso.oidc.api.service.authorization.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author syriana.zh
 * @date 2020/06/22
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    SelfAuthenticationProvider selfAuthenticationProvider;
    @Autowired
    SelfBcryptPswEncoder selfBcryptPswEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter: off
        auth.userDetailsService(userDetailServiceImpl); // 自定义用户验证
        auth.authenticationProvider(selfAuthenticationProvider);// 自定义用户校验器
        auth.inMemoryAuthentication()
                .withUser("hellxz")
                .password(selfBcryptPswEncoder.encode("xyz"))
                .authorities(Collections.emptyList());
        // @formatter: on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //所有请求都需要通过认证
                .and()
                .httpBasic() //Basic登录
                .and()
                .csrf().disable(); //关跨域保护
    }
}
