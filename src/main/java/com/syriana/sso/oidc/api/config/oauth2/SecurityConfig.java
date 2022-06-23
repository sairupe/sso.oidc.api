package com.syriana.sso.oidc.api.config.oauth2;

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
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

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
                .antMatchers("/swagger-ui.html").permitAll() //swagger放开
                .antMatchers("/swagger-resources/**").permitAll() //swagger放开
                .antMatchers("/webjars/**").permitAll() //swagger放开
                .antMatchers("/v2/**").permitAll() //swagger放开
                .antMatchers("/rpc/**").permitAll() // rpc请求放开
                .antMatchers("/redis/**").permitAll() // redis请求放开
                .anyRequest().authenticated() //所有请求都需要通过认证
                .and()
                // Basic登录, 如果设置了这个，必须要在头里加上才能访问  --似乎解释不对
                // 如果客户端是浏览器，那么此时就会弹出⼀个弹窗，让⽤户输⼊⽤户名和密码 请求
                // Basic BASE64(userName:password)

                // GET /family/son.jpg  HTTP/1.1
                //Authorization: Basic U2h1c2hlbmcwMDcldUZGMUFzczAwNw==

                // client_id 和 client_secreat必须在head中的Authorization中BASE 64 ENCODE
                // 正确的解释，弹出HTTP自己的密码登录框
//                .httpBasic()
//                .and()

                // 表单登录, 开启就是SPRINGBOOT的页面，不开启就是浏览器弹框 --似乎解释不对
                // 弹出自己自定义登录框
                // client_id 和 client_secreat可以在表单中提交
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .csrf().disable(); //关跨域保护
    }
}
