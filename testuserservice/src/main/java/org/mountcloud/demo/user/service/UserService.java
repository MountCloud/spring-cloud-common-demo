package org.mountcloud.demo.user.service;

import org.mountcloud.demo.user.dao.UserDao;
import org.mountcloud.demo.user.entity.User;
import org.mountcloud.demo.user.entity.UserExample;
import org.mountcloud.mybatisplugin.MybatisUtil;
import org.mountcloud.springcloud.common.mybatis.service.BaseMybatisService;
import org.springframework.stereotype.Service;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: 用户servcie
 * 2020/1/20.
 */
@Service
public class UserService extends BaseMybatisService<User, UserDao> {

    /**
     * 这个方法就是实体转查询条件，框架提供了一个默认的设置方法，就是 MybatisUtil.setAutoExample
     * @param bean
     * @return
     */
    @Override
    public UserExample getExample(User bean) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        MybatisUtil.setAutoExample(criteria,bean);
        return userExample;
    }

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return
     */
    public User findUserByUserName(String userName){
        User user = new User();
        //设置查询条件
        user.setUsername(userName);

        user = findOne(user);
        return user;
    }
}
