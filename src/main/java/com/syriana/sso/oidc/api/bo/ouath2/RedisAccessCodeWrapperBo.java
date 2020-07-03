package com.syriana.sso.oidc.api.bo.ouath2;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author syriana.zh
 * @date 2020/07/03
 */
@Data
public class RedisAccessCodeWrapperBo {
    /**
     * authentication 原始序列化字节(没有无参构造无法反序列化)
     */
    private byte[] authenticationBytes;
    /**
     * authentication JSON信息
     */
    private String authenticationJson;
}
