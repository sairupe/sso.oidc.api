package com.syriana.sso.oidc.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author syriana.zh
 * @date 2020/06/24
 */
@Configuration
public class TokenConfig {

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
