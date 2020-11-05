package com.syriana.sso.oidc.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sso.oidc.srv.ApiClient;
import sso.oidc.srv.controller.RedisControllerApi;
import sso.oidc.srv.controller.RpcTestControllerApi;

import javax.annotation.PostConstruct;

/**
 * @author syriana.zh
 * @date 2020/11/01
 */
@Configuration
public class SSOOIDCSrvClient {

    private ApiClient apiClient;

    @PostConstruct
    public void init(){
        apiClient = new ApiClient();
    }

    @Bean
    public RedisControllerApi getRedisControllerApi(){
        RedisControllerApi redisControllerApi = apiClient.buildClient(RedisControllerApi.class);
        return redisControllerApi;
    }

    @Bean
    public RpcTestControllerApi getRpcTestControllerApi(){
        RpcTestControllerApi redisControllerApi = apiClient.buildClient(RpcTestControllerApi.class);
        return redisControllerApi;
    }
}
