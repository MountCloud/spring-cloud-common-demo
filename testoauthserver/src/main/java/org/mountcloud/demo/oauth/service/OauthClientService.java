package org.mountcloud.demo.oauth.service;

import org.mountcloud.demo.oauth.entity.OauthClient;
import org.mountcloud.demo.oauth.repository.OauthClientRepository;
import org.mountcloud.springcloud.common.mongo.service.BaseMongoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.service
 * TODO:
 * 2020年1月7日.
 */
@Service
public class OauthClientService extends BaseMongoService<OauthClient, OauthClientRepository>{
	
	@Cacheable(key = "#clientId",value = "findClientByClientId")
	public OauthClient findClientByClientId(String clientId) {
		Query query = new Query(Criteria.where("clientId").is(clientId));
		OauthClient client = mongoTemplate.findOne(query, OauthClient.class);
		return client;
	}

}
