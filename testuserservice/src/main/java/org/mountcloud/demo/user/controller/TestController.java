package org.mountcloud.demo.user.controller;

import org.mountcloud.springproject.common.result.RestfulResult;
import org.mountcloud.springproject.common.result.ResultData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO:
 * 2020/1/20.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试系统角色权限
     * @return
     */
    @PreAuthorize("hasRole('SYSTEM')")
    @GetMapping("/role/system")
    public RestfulResult<ResultData<String>> getTestSystem(){
        RestfulResult<ResultData<String>> restfulResult = new RestfulResult<ResultData<String>>();

        ResultData<String> resultData = new ResultData<String>();
        resultData.setData("success");

        restfulResult.setData(resultData);
        return restfulResult;
    }

    /**
     * 测试管理员权限
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/role/admin")
    public RestfulResult<ResultData<String>> getTestAdmin(){
        RestfulResult<ResultData<String>> restfulResult = new RestfulResult<ResultData<String>>();

        ResultData<String> resultData = new ResultData<String>();
        resultData.setData("success");

        restfulResult.setData(resultData);
        return restfulResult;
    }

    /**
     * 测试普通用户权限
     * @return
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/role/user")
    public RestfulResult<ResultData<String>> getTestUser(){
        RestfulResult<ResultData<String>> restfulResult = new RestfulResult<ResultData<String>>();

        ResultData<String> resultData = new ResultData<String>();
        resultData.setData("success");

        restfulResult.setData(resultData);
        return restfulResult;
    }
}
