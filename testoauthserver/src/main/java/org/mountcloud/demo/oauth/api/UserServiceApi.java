package org.mountcloud.demo.oauth.api;

import org.mountcloud.demo.user.entity.User;
import org.mountcloud.springproject.common.result.RestfulResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.api
 * TODO:
 * 2020年1月3日.
 */

@FeignClient(name="demo-user-service")
public interface UserServiceApi {

	@GetMapping("/user/findbyusername")
	public RestfulResult<User> getUserByUserName(@RequestParam("userName") String userName);
}
