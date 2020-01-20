package org.mountcloud.demo.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


/**
  * @author zhanghaishan
  * @version V1.0
  *
  * TODO: 服务的资源配置
  * 2020/1/20.
  */
@Configuration
@EnableResourceServer
@Order(30)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/actuator/**").permitAll().and().httpBasic();
	}

}
