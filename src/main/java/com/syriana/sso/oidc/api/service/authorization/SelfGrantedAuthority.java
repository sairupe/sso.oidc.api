package com.syriana.sso.oidc.api.service.authorization;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author syriana.zh
 * @date 2020/06/24
 */
public class SelfGrantedAuthority implements GrantedAuthority {

    private String authroity;

    public SelfGrantedAuthority(String authroity) {
        this.authroity = authroity;
    }

    @Override
    public String getAuthority() {
        return authroity;
    }
}
