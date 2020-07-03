package com.syriana.sso.oidc.api.service.authorization;

import com.syriana.sso.oidc.api.bo.ouath2.ErpUserDetailBo;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author syriana.zh
 * @date 2020/06/30
 */
@Data
public class SelfAuth implements Authentication {

    private ErpUserDetailBo erpUserDetail;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return erpUserDetail.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return erpUserDetail.getPassword();
    }

    @Override
    public Object getDetails() {
        return erpUserDetail;
    }

    @Override
    public Object getPrincipal() {
        return erpUserDetail;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        return;
    }

    @Override
    public String getName() {
        return erpUserDetail.getUsername();
    }
}
