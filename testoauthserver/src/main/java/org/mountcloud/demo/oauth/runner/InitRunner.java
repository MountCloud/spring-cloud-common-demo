package org.mountcloud.demo.oauth.runner;

import java.util.Arrays;


import org.mountcloud.demo.oauth.entity.OauthClient;
import org.mountcloud.demo.oauth.service.OauthClientService;
import org.mountcloud.demo.oauth.type.ClientType;
import org.mountcloud.springcloud.common.oauth2feigh.config.RoleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.runner
 * TODO:
 * 2020年1月8日.
 */
@Component
public class InitRunner implements ApplicationRunner {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private OauthClientService oauthClientService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		initSystemClient();
	}
	
	private void initSystemClient() {
		String systemClientId = env.getProperty("security.oauth2.client.client-id");
		String systemClientSecret = env.getProperty("security.oauth2.client.client-secret");

		if(systemClientId!=null&&systemClientSecret!=null) {
			if(oauthClientService.findClientByClientId(systemClientId)==null) {
				
				OauthClient oauthClient = new OauthClient();
				oauthClient.setClientId(systemClientId);
				oauthClient.setClientSecret(passwordEncoder.encode(systemClientSecret));
				oauthClient.setName("系统API Client");
				oauthClient.setDescribe("用于系统中服务之间相互调用");
				oauthClient.setType(ClientType.SYSTEM);
				oauthClient.setScopes("all");
				oauthClient.setGrantTypes("client_credentials");
				oauthClient.setAuthorities(RoleConfig.SYSTEM_ROLE);
		
				oauthClientService.save(oauthClient);
			}
		}
	}

}
