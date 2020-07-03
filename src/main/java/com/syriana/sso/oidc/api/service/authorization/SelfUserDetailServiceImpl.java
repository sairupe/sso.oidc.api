package com.syriana.sso.oidc.api.service.authorization;

import com.syriana.sso.oidc.api.bo.ouath2.ErpUserDetailBo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author syriana.zh
 * @date 2020/06/23
 */
@Configuration
public class SelfUserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ErpUserDetailBo userDetails = new ErpUserDetailBo();
        userDetails.setUserName("12315");
        userDetails.setPassword("12315");
        userDetails.setFlag(true);
        List<SelfGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SelfGrantedAuthority("p1"));
        userDetails.setAuthorities(authorityList);
        return userDetails;
    }
}
