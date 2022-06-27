package com.syriana.sso.oidc.api.service.authorization;

import com.syriana.sso.oidc.api.bo.ouath2.ErpUserDetailBo;
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
    SelfUserDetailServiceImpl selfUserDetailService;
    @Autowired
    SelfBcryptPswEncoder selfBcryptPswEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // 获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 获取封装用户信息的对象
        UserDetails userDetails = selfUserDetailService.loadUserByUsername(username);
        String dbUserPassword = userDetails.getPassword();
//        if (!selfBcryptPswEncoder.matches(password, dbUserPassword)) {
//           throw new BadCredentialsException("密码错误!");
//        }
        ErpUserDetailBo erpUserDetail = (ErpUserDetailBo) userDetails;
        SelfAuth selfAuth = new SelfAuth();
        selfAuth.setErpUserDetail(erpUserDetail);
        return selfAuth;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
