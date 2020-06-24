package com.syriana.sso.oidc.api.service.authorization;

import com.syriana.sso.oidc.api.exception.IncorrectPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author syriana.zh
 * @date 2020/06/23
 */
@Configuration
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailServiceImpl userDetailService;
    @Autowired
    SelfBcryptPswEncoder selfBcryptPswEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // 获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 获取封装用户信息的对象
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        boolean isMatch = selfBcryptPswEncoder.matches(password, userDetails.getPassword());
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
