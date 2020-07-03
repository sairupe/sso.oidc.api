package com.syriana.sso.oidc.api.service.authorization;

import com.alibaba.fastjson.JSONObject;
import com.syriana.sso.oidc.api.bo.ouath2.RedisAccessCodeWrapperBo;
import com.syriana.sso.oidc.api.config.redis.RedisKeyGenerator;
import com.syriana.sso.oidc.api.constant.Constant;
import com.syriana.sso.oidc.api.utils.redis.RedisTplUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

/**
 * @author syriana.zh
 * @date 2020/07/03
 */
@Configuration
public class SelfAuthorizationCodeServicesImpl implements AuthorizationCodeServices {

    // 系统默认的生成器，后期替换
    private RandomValueStringGenerator generator = new RandomValueStringGenerator();

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String accessCode = generator.generate();
        String key = RedisKeyGenerator.buildAccessCodeKey(accessCode);
        RedisAccessCodeWrapperBo wrapperBo = new RedisAccessCodeWrapperBo();
        wrapperBo.setAccessCode(accessCode);
        wrapperBo.setOAuth2Authentication(authentication);
        RedisTplUtils.setByKeyExpire(key, wrapperBo, Constant.ACCESS_CODE_EXPIRE_SECONDS);
        return accessCode;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
        String accessCodeKey = RedisKeyGenerator.buildAccessCodeKey(code);
//        String wrapperBo = RedisTplUtils.getByKey(accessCodeKey);
//        RedisAccessCodeWrapperBo wrapper = JSONObject.parseObject(wrapperBo, RedisAccessCodeWrapperBo.class);
        RedisAccessCodeWrapperBo wrapper = RedisTplUtils.getByKey(accessCodeKey);
        OAuth2Authentication oAuth2Authentication = wrapper.getOAuth2Authentication();
        return oAuth2Authentication;
    }
}
