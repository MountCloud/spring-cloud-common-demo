package org.mountcloud.demo.user.controller;

import org.mountcloud.demo.user.entity.User;
import org.mountcloud.demo.user.service.UserService;
import org.mountcloud.springcloud.mvc.common.controller.BaseController;
import org.mountcloud.springproject.common.result.RestfulResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: 测试用户controller
 * 2020/1/20.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {

    /**
     * 根据用户名返回用户信息
     * @param userName 用户名
     * @return
     */
    @GetMapping("/findbyusername")
    @PreAuthorize("hasRole('ADMIN')")
    public RestfulResult<User> getUserByUserName(@RequestParam("userName") String userName){
        RestfulResult<User> restfulResult = new RestfulResult<User>();
        User user = service.findUserByUserName(userName);
        restfulResult.setData(user);
        return restfulResult;
    }

}
