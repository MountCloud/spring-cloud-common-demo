package org.mountcloud.demo.oauth.service;

import java.util.ArrayList;
import java.util.List;

import org.mountcloud.demo.oauth.api.UserServiceApi;
import org.mountcloud.demo.user.entity.User;
import org.mountcloud.springcloud.common.oauth2feigh.entity.BaseUserDetails;
import org.mountcloud.springproject.common.result.RestfulResult;
import org.mountcloud.springproject.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.service
 * TODO:
 * 2020年1月6日.
 */
@Service("userApiDetailsService")
public class UserApiDetailsService implements UserDetailsService{

	@Autowired
	private UserServiceApi userServiceApi;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BaseUserDetails userDetails = new BaseUserDetails();
		
		RestfulResult<User> userResult = userServiceApi.getUserByUserName(username);
		User user = ResultUtil.getResult(userResult);
		if(user==null) {
			throw new UsernameNotFoundException(username+" is not found.");
		}
		userDetails.setUserId(user.getId());
		userDetails.setUsername(username);
		userDetails.setPassword(user.getPassword());

		String[] roles = user.getRoles().split(",");
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		for(String role : roles) {
			GrantedAuthority ga = new SimpleGrantedAuthority(role);
			authorityList.add(ga);
		}
		
		userDetails.setAuthorities(authorityList);
		
		return userDetails;
	}

}
