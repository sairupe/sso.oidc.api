package com.syriana.sso.oidc.api.service.authorization;

import com.alibaba.fastjson.JSONObject;
import com.syriana.sso.oidc.api.bo.ouath2.RedisAccessCodeWrapperBo;
import com.syriana.sso.oidc.api.config.redis.RedisKeyGenerator;
import com.syriana.sso.oidc.api.constant.Constant;
import com.syriana.sso.oidc.api.utils.redis.RedisTplUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

/**
 * @author syriana.zh
 * @date 2020/07/03
 */
@Configuration
public class SelfAuthorizationCodeServicesImpl implements AuthorizationCodeServices {

    // 系统默认的生成器，后期替换
    private RandomValueStringGenerator generator = new RandomValueStringGenerator(12);

    // OAuth2Authentication没有无参构造函数，只能用最原始的序列化方式
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String accessCode = generator.generate();
        String key = RedisKeyGenerator.buildAccessCodeKey(accessCode);
        byte[] serialize = SerializationUtils.serialize(authentication);
        RedisAccessCodeWrapperBo redisAccessCodeWrapperBo = new RedisAccessCodeWrapperBo();
        redisAccessCodeWrapperBo.setAuthenticationBytes(serialize);
        redisAccessCodeWrapperBo.setAuthenticationJson(JSONObject.toJSONString(authentication));
        RedisTplUtils.setByKeyExpire(key, redisAccessCodeWrapperBo, Constant.ACCESS_CODE_EXPIRE_SECONDS);
        return accessCode;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
        String accessCodeKey = RedisKeyGenerator.buildAccessCodeKey(code);
        RedisAccessCodeWrapperBo redisAccessCodeWrapperBo = RedisTplUtils.getByKey(accessCodeKey);
        byte[] serialize = redisAccessCodeWrapperBo.getAuthenticationBytes();
        OAuth2Authentication OAuth2Authentication = SerializationUtils.deserialize(serialize);
        RedisTplUtils.deleteKey(accessCodeKey);
        return OAuth2Authentication;
    }
}
