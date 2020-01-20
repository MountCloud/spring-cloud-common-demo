package org.mountcloud.demo.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mountcloud.demo.oauth.entity.OauthClient;
import org.mountcloud.demo.oauth.service.OauthClientService;
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
@SpringBootTest(classes = {Oauth2SApplicationApp.class})// 指定启动类
public class InitData {

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void initData(){
        OauthClient oauthClient = new OauthClient();

        oauthClient.setClientId("testclient");
        oauthClient.setName("测试客户端");
        oauthClient.setDescribe("测试客户端");
        oauthClient.setClientSecret(passwordEncoder.encode("testsecret"));
        oauthClient.setScopes("all");
        oauthClient.setGrantTypes("password,authorization_code,refresh_token");

        oauthClientService.save(oauthClient);

    }
}
