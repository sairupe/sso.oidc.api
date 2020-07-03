package com.syriana.sso.oidc.api.bo.ouath2;

import lombok.Data;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author syriana.zh
 * @date 2020/07/03
 */
@Data
public class RedisAccessCodeWrapperBo {
    /**
     * 授权码
     */
    private String accessCode;
    /**
     * 认证信息
     */
    private OAuth2Authentication oAuth2Authentication;
}
