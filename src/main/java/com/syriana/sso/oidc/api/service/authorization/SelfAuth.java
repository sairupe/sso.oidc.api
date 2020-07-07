package com.syriana.sso.oidc.api.service.authorization;

import com.syriana.sso.oidc.api.bo.ouath2.ErpUserDetailBo;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

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

    // 兼容TOKEN:AUTH 字段反序列化报错，
    // REDIS TOKEN JSON中有authorities字段，反序列化要调用set这里默认处理下？
    public void setAuthorities(List list){

    }

    @Override
    public Object getCredentials() {
        return erpUserDetail.getPassword();
    }

    @Override
    public Object getDetails() {
        return "details";
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
