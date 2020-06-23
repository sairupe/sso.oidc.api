package com.syriana.sso.oidc.api.service.authorization;

import com.syriana.sso.oidc.api.bo.ErpUserDetail;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * @author syriana.zh
 * @date 2020/06/23
 */
@Configuration
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        if (true) {
//            throw new BadCredentialsException("用户未查询到!");
//        }
        ErpUserDetail userDetails = new ErpUserDetail();
        userDetails.setUserName("12315");
        userDetails.setPassword("12315");
        userDetails.setFlag(true);
        return userDetails;
    }
}
