package org.mountcloud.demo.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mountcloud.demo.user.entity.User;
import org.mountcloud.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO:
 * 2020/1/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserServiceApplication.class})// 指定启动类
public class InitData {

    //框架封装的密码加密工具，位于org.mountcloud.springcloud包下配置为Bean
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Test
    public void initData(){
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.setRoles("ROLE_ADMIN");
        adminUser = userService.save(adminUser);
        System.out.println("admin id is"+adminUser.getId());

        User userUser = new User();
        userUser.setUsername("user");
        userUser.setPassword(passwordEncoder.encode("user"));
        userUser.setRoles("ROLE_UESR");
        userUser = userService.save(userUser);
        System.out.println("user id is:"+userUser.getId());
    }

}
