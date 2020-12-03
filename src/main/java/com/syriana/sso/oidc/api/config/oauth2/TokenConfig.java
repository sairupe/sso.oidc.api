package com.syriana.sso.oidc.api.config.oauth2;

import com.syriana.sso.oidc.api.config.oauth2.fastjson.FastjsonRedisTokenStoreSerializationStrategy;
import com.syriana.sso.oidc.api.config.redis.RedisKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author syriana.zh
 * @date 2020/06/24
 */
@Configuration
public class TokenConfig {

    private String SIGNING_KEY = "SALT";

    @Autowired
    private RedisConnectionFactory connectionFactory;

//    // 内存TOKEN
//    @Bean
//    public TokenStore tokenStore(){
//        return new InMemoryTokenStore();
//    }

//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(connectionFactory);
        redisTokenStore.setPrefix(RedisKeyGenerator.KEY_ACCESS_TOEKN);
        redisTokenStore.setSerializationStrategy(new FastjsonRedisTokenStoreSerializationStrategy());
        return redisTokenStore;
    }

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        MyJwtConverter converter = new MyJwtConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
}
