package org.mountcloud.demo.oauth.controller;

import java.util.List;

import org.mountcloud.demo.oauth.entity.OauthClient;
import org.mountcloud.demo.oauth.service.OauthClientService;
import org.mountcloud.springcloud.common.oauth2feigh.entity.BaseUserDetails;
import org.mountcloud.springproject.common.result.RestfulResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaishan
 * @version V1.0 org.mountcloud.server.oauth2.controller TODO: 2020年1月7日.
 */
@RestController
@RequestMapping("/user")
public class OauthUserController {
	
	@Autowired
	private OauthClientService oauthClientService;

	/**
	 * 自己实现的查看token用户的接口
	 * @return
	 */
	@GetMapping("/me")
	public RestfulResult<BaseUserDetails> getMe() {
		RestfulResult<BaseUserDetails> restfulResult = new RestfulResult<BaseUserDetails>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof OAuth2Authentication) {
			
			OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
			OAuth2Request request = oAuth2Auth.getOAuth2Request();
			
			String clientId = request.getClientId();

			authentication = oAuth2Auth.getUserAuthentication();
			if (authentication instanceof UsernamePasswordAuthenticationToken) {
				UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
				Object principal = authentication.getPrincipal();
				if (principal instanceof BaseUserDetails) {
					restfulResult.setData((BaseUserDetails)principal);
				}
			}
			
			if(restfulResult.getData()==null) {
				OauthClient client = oauthClientService.findClientByClientId(clientId);
				
				BaseUserDetails baseUserDetails = new BaseUserDetails();
				baseUserDetails.setUsername(clientId);
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities());
				baseUserDetails.setAuthorities(grantedAuthorities);
				
				restfulResult.setData(baseUserDetails);
			}
		}

		return restfulResult;
	}

}
