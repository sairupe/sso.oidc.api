package com.syriana.sso.oidc.api.bo;

import com.syriana.sso.oidc.api.service.authorization.SelfGrantedAuthority;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author syriana.zh
 * @date 2020/06/23
 */
@Data
public class ErpUserDetail implements UserDetails {

    private String userName;

    private String password;

    private boolean isFlag;

    private List<SelfGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userName;
    }

    @Override
    public String getUsername() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isFlag;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isFlag;
    }
}
