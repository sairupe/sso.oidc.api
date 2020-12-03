package com.syriana.sso.oidc.api.config.oauth2;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 测试自定义TOKEN，未完成
 * @author syriana.zh
 * @date 2020/12/03
 */
@Deprecated
@Data
public class MyOAuth2AccessToken implements Serializable, OAuth2AccessToken {

    private String value;

    private OAuth2RefreshToken refreshToken;

    private Date expiration;

    public MyOAuth2AccessToken(String value) {
        this.value = value;
    }


    public MyOAuth2AccessToken(OAuth2AccessToken accessToken) {
        this(accessToken.getValue());
        setRefreshToken(accessToken.getRefreshToken());
        setExpiration(accessToken.getExpiration());
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public Set<String> getScope() {
        return null;
    }

    @Override
    public OAuth2RefreshToken getRefreshToken() {
        return null;
    }

    @Override
    public String getTokenType() {
        return null;
    }

    @Override
    public boolean isExpired() {
        return expiration != null && expiration.before(new Date());
    }

    @Override
    public Date getExpiration() {
        return expiration;
    }

    @Override
    public int getExpiresIn() {
        return 0;
    }

    @Override
    public String getValue() {
        return value;
    }
}
