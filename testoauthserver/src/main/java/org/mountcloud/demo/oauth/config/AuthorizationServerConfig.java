package org.mountcloud.demo.oauth.config;


import org.mountcloud.demo.oauth.service.MongoClientDetailsService;
import org.mountcloud.demo.oauth.service.UserApiDetailsService;
import org.mountcloud.springcloud.common.redis.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.config
 * TODO: oauth2 配置
 * 2020年1月3日.
 */
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private UserApiDetailsService userDetailsService;
    
    @Autowired
    private MongoClientDetailsService mongoClientDetailsService;
    
    @Autowired
    private RedisConfig redisConfig;
    
    @Bean
    public RedisTokenStore tokenStore() {
    	RedisTokenStore redisTokenStore = new RedisTokenStore(connectionFactory);
    	redisTokenStore.setPrefix(redisConfig.getRedisKeyPrefix());
        return redisTokenStore;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	
    	clients.withClientDetails(mongoClientDetailsService);
    }
}
