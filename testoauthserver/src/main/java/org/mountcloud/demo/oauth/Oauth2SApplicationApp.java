package org.mountcloud.demo.oauth;

import org.mountcloud.springcloud.common.oauth2feigh.EnableOAuth2FeighClient;
import org.mountcloud.springcloud.common.redis.EnableRedisCache;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


@SpringCloudApplication
@EnableAuthorizationServer
@EnableOAuth2FeighClient
@EnableFeignClients
@EnableRedisCache
@ComponentScan(basePackages={"org.mountcloud.demo.oauth","org.mountcloud.springcloud"})
public class Oauth2SApplicationApp {
	public static void main(String[] args) {
		SpringApplication.run(Oauth2SApplicationApp.class, args);
	}
}
