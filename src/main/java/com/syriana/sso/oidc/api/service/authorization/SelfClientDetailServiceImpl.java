package com.syriana.sso.oidc.api.service.authorization;

import com.syriana.sso.oidc.api.dao.ClientDetailsMapper;
import com.syriana.sso.oidc.api.entity.ClientDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

/**
 * @author syriana.zh
 * @date 2020/07/02
 */
@Configuration
@Component("clientDetailsServiceSelf")
public class SelfClientDetailServiceImpl implements ClientDetailsService {

    @Autowired
    ClientDetailsMapper clientDetailsMapper;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        ClientDetailsEntity clientDetailsEntity = clientDetailsMapper.getByClientId(s);
        if (clientDetailsEntity == null) {
            return null;
        }
        String clientId = clientDetailsEntity.getClientId();
        String resourceIds = clientDetailsEntity.getResourceIds();
        String scope = clientDetailsEntity.getScope();
        String authorizedGrantTypes = clientDetailsEntity.getAuthorizedGrantTypes();
        String authorities = clientDetailsEntity.getAuthorities();
        String webServerRedirectUri = clientDetailsEntity.getWebServerRedirectUri();
        String clientSecret = clientDetailsEntity.getClientSecret();
        Integer accessTokenValidity = clientDetailsEntity.getAccessTokenValidity();
        Integer refreshTokenValidity = clientDetailsEntity.getRefreshTokenValidity();
        BaseClientDetails clientDetails = new BaseClientDetails(clientId, resourceIds, scope, authorizedGrantTypes, authorities, webServerRedirectUri);
        clientDetails.setClientSecret(clientSecret);
        clientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity);
        clientDetails.setAccessTokenValiditySeconds(accessTokenValidity);
        return clientDetails;
    }
}
