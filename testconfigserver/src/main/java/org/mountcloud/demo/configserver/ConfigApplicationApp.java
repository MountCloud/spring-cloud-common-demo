package org.mountcloud.demo.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringCloudApplication
@EnableConfigServer
public class ConfigApplicationApp {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplicationApp.class, args);
	}
}
