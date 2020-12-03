package com.syriana.sso.oidc.api.config.oauth2;

import com.syriana.sso.oidc.api.bo.ouath2.ErpUserDetailBo;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 测试自定义TOKEN，未完成
 *
 * @author syriana.zh
 * @date 2020/12/03
 */
@Deprecated
public class MyJwtConverter extends JwtAccessTokenConverter {

    private JsonParser objectMapper = JsonParserFactory.create();

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        ErpUserDetailBo details = (ErpUserDetailBo) authentication.getPrincipal();
        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
        info.put("iat", System.currentTimeMillis() / 1000);
        info.put("sub", details.getUserId());
        DefaultOAuth2AccessToken defaultToken = (DefaultOAuth2AccessToken) accessToken;
        defaultToken.setAdditionalInformation(info);
        return super.enhance(accessToken, authentication);
    }
}
