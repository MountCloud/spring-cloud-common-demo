package org.mountcloud.demo.oauth.service;

import org.mountcloud.demo.oauth.client.BaseClient;
import org.mountcloud.demo.oauth.entity.OauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.config
 * TODO: client放到mongo便于管理
 * 2020年1月6日.
 */
@Service("mongoClientDetailsService")
public class MongoClientDetailsService implements ClientDetailsService{
	
	@Autowired
	private OauthClientService oauthClientService;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetailsService#loadClientByClientId(java.lang.String)
	 */
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		OauthClient oauthClient = oauthClientService.findClientByClientId(clientId);;

		BaseClient client = new BaseClient(oauthClient.getClientId(), oauthClient.getResourceIds(), oauthClient.getScopes(), oauthClient.getGrantTypes(), oauthClient.getAuthorities(), oauthClient.getRedirectUris());
		client.setName(oauthClient.getName());
		client.setClientSecret(oauthClient.getClientSecret());
		client.setType(oauthClient.getType());
		client.setDescribe(oauthClient.getDescribe());
		client.setAccessTokenValiditySeconds(oauthClient.getAccessTokenValiditySeconds());
		client.setRefreshTokenValiditySeconds(oauthClient.getRefreshTokenValiditySeconds());
		
		return client;
	}
}
