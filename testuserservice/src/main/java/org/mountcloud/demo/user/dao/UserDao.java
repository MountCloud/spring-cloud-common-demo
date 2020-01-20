package org.mountcloud.demo.user.dao;

import org.mountcloud.demo.user.entity.User;
import org.mountcloud.demo.user.mapper.UserMapper;
import org.mountcloud.springcloud.common.mybatis.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: userdao
 * 2020/1/20.
 */
@Repository
public class UserDao extends BaseDao<User, UserMapper> {
}
