package org.mountcloud.demo.user;

import org.mountcloud.springcloud.common.mybatis.EnableMyBatis;
import org.mountcloud.springcloud.common.oauth2feigh.EnableOAuth2FeighClient;
import org.mountcloud.springcloud.common.oauth2feigh.EnableOauthResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO:
 * 2020/1/20.
 */
@EnableDiscoveryClient
@EnableOAuth2FeighClient
@SpringBootApplication
@EnableOauthResourceServer
@EnableMyBatis
@ComponentScan(basePackages={"org.mountcloud.demo.user","org.mountcloud.springcloud"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
