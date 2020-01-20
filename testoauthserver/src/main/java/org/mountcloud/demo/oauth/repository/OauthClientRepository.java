package org.mountcloud.demo.oauth.repository;


import org.mountcloud.demo.oauth.entity.OauthClient;
import org.mountcloud.springcloud.common.mongo.repository.BaseMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.repository
 * TODO:
 * 2020年1月7日.
 */
@Repository
public interface OauthClientRepository extends BaseMongoRepository<OauthClient>{

}
