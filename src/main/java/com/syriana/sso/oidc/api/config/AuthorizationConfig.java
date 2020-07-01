package com.syriana.sso.oidc.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author syriana.zh
 * @date 2020/06/22
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    DataSource dataSource;
    @Autowired
    @Qualifier("clientDetailsServiceSelf")
    ClientDetailsService clientDetailsServiceImpl;

    // 令牌访问端点配置和令牌服务配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)// 密码认证模式必须配一个authenticationManager
                .authorizationCodeServices(authorizationCodeServices)// 授权码模式需要
                .tokenServices(tokenServices())// 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);// 允许POST提交访问
    }

    // 令牌安全约束配置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单提交
//        security.allowFormAuthenticationForClients()
////                .checkTokenAccess("isAuthenticated()");
////                .tokenKeyAccess("permitAll()");
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")// 检测解析令牌
                .allowFormAuthenticationForClients();// 表单认证申请令牌
    }

    // 客户端详情配置  建议在数据库生成
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("client-a") //client端唯一标识
//                .secret(passwordEncoder.encode("client-a-secret")) //客户端的密码，这里的密码应该是加密后的
//                .authorizedGrantTypes("authorization_code", "password", "refresh_token") //授权模式标识
//                .scopes("read_user_info") //作用域
//                .resourceIds("resource1") //资源id
//                .autoApprove(false) // 是否自动授权
//                .redirectUris("http://www.baidu.com");//回调地址
        clients.withClientDetails(clientDetailsServiceImpl);
    }

    /**
     * 不指定别名会报这个错，我也不知道为什么，曲线救国别名注入
     * The bean 'clientDetailsService',
     * defined in class path resource [com/syriana/sso/oidc/api/config/AuthorizationConfig.class],
     * could not be registered.
     * A bean with that name has already been defined in BeanDefinition defined in class path
     * resource [org/springframework/security/oauth2/config/annotation/configuration/ClientDetailsServiceConfiguration.class]
     * and overriding is disabled.
     */
    @Bean("clientDetailsServiceSelf")
    ClientDetailsService clientDetailsService(){
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

    // 领牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsServiceImpl);// 客户端详情服务
        services.setSupportRefreshToken(true);// 支持刷新令牌
        services.setTokenStore(tokenStore);// 令牌储存策略
        // 设置令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);
        // 有效和刷新时间
        services.setAccessTokenValiditySeconds(7200);
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }

    // 授权码服务   建议在数据库生成
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new InMemoryAuthorizationCodeServices();
//    }
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
