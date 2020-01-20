package org.mountcloud.demo.oauth.config;

import org.mountcloud.demo.oauth.service.UserApiDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
  * @author zhanghaishan
  * @version V1.0
  *
  * TODO: oauth的权限控制，与ResourceServerConfigurerAdapter的区别就是验证顺序不一样，目前经过调试他们的HttpSecurity也不是一个实例，也就是说它们是两个同时作用互不相干的安全限制，需要根据自己的业务来选择将限制放在那里。
 *  WebSecurityConfigurerAdapter的Order是100，
 *  ResourceServerConfigurerAdapter的默认Order是3，在ResourceServerConfiguration中可以看到
  * 2020/1/20.
  */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserApiDetailsService userApiDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userApiDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
		.and()
			.csrf()
			.disable()
			.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	}
}
