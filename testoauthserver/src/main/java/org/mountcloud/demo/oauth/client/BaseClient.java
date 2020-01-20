package org.mountcloud.demo.oauth.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.Jackson2ArrayOrStringDeserializer;
import org.springframework.security.oauth2.provider.client.JacksonArrayOrStringDeserializer;
import org.springframework.util.StringUtils;

/**
 * oauth的客户端，存在数据库里，比如pc站，wap站，安卓，ios，微信登录等等
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.server.oauth2.entity
 * TODO:
 * 2020年1月7日.
 */
@SuppressWarnings("serial")
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_DEFAULT)
@org.codehaus.jackson.annotate.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class BaseClient implements ClientDetails{
	
	//名字
	private String name;
	//描述
	private String describe;
	//客户端类型  ClientType
	private String type;
	
	//客户端id
	@org.codehaus.jackson.annotate.JsonProperty("client_id")
	@com.fasterxml.jackson.annotation.JsonProperty("client_id")
	private String clientId;

	//客户端密码
	@org.codehaus.jackson.annotate.JsonProperty("client_secret")
	@com.fasterxml.jackson.annotation.JsonProperty("client_secret")
	private String clientSecret;

	//客户端访问范围
	@org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
	private Set<String> scope = Collections.emptySet();

	//允许访问那些资源，每个服务可以设置一个资源id
	@org.codehaus.jackson.annotate.JsonProperty("resource_ids")
	@org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
	@com.fasterxml.jackson.annotation.JsonProperty("resource_ids")
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
	private Set<String> resourceIds = Collections.emptySet();

	//授权方式client_credentials,password,authorization_code,refresh_token
	@org.codehaus.jackson.annotate.JsonProperty("authorized_grant_types")
	@org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
	@com.fasterxml.jackson.annotation.JsonProperty("authorized_grant_types")
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
	private Set<String> authorizedGrantTypes = Collections.emptySet();

	//跳转地址
	@org.codehaus.jackson.annotate.JsonProperty("redirect_uri")
	@org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
	@com.fasterxml.jackson.annotation.JsonProperty("redirect_uri")
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
	private Set<String> registeredRedirectUris;

	//设置用户是否自动Approval操作, 默认值为 false,可选值包括 true,false, read,write.该字段只适用于grant_type="authorization_code的情况,当用户登录成功后,若该值为true或支持的scope值,则会跳过用户Approve的页面, 直接授权.
	@org.codehaus.jackson.annotate.JsonProperty("autoapprove")
	@org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
	@com.fasterxml.jackson.annotation.JsonProperty("autoapprove")
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
	private Set<String> autoApproveScopes;

	//指定客户端所拥有的Spring Security的权限值。
	private List<GrantedAuthority> authorities = Collections.emptyList();

	//设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
	@org.codehaus.jackson.annotate.JsonProperty("access_token_validity")
	@com.fasterxml.jackson.annotation.JsonProperty("access_token_validity")
	private Integer accessTokenValiditySeconds;

	//设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
	@org.codehaus.jackson.annotate.JsonProperty("refresh_token_validity")
	@com.fasterxml.jackson.annotation.JsonProperty("refresh_token_validity")
	private Integer refreshTokenValiditySeconds;

	//这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据
	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();

	public BaseClient() {
	}

	public BaseClient(ClientDetails prototype) {
		this();
		setAccessTokenValiditySeconds(prototype.getAccessTokenValiditySeconds());
		setRefreshTokenValiditySeconds(prototype
				.getRefreshTokenValiditySeconds());
		setAuthorities(prototype.getAuthorities());
		setAuthorizedGrantTypes(prototype.getAuthorizedGrantTypes());
		setClientId(prototype.getClientId());
		setClientSecret(prototype.getClientSecret());
		setRegisteredRedirectUri(prototype.getRegisteredRedirectUri());
		setScope(prototype.getScope());
		setResourceIds(prototype.getResourceIds());
	}

	public BaseClient(String clientId, String resourceIds,
			String scopes, String grantTypes, String authorities) {
		this(clientId, resourceIds, scopes, grantTypes, authorities, null);
	}

	public BaseClient(String clientId, String resourceIds,
			String scopes, String grantTypes, String authorities,
			String redirectUris) {

		this.clientId = clientId;

		if (StringUtils.hasText(resourceIds)) {
			Set<String> resources = StringUtils
					.commaDelimitedListToSet(resourceIds);
			if (!resources.isEmpty()) {
				this.resourceIds = resources;
			}
		}

		if (StringUtils.hasText(scopes)) {
			Set<String> scopeList = StringUtils.commaDelimitedListToSet(scopes);
			if (!scopeList.isEmpty()) {
				this.scope = scopeList;
			}
		}

		if (StringUtils.hasText(grantTypes)) {
			this.authorizedGrantTypes = StringUtils
					.commaDelimitedListToSet(grantTypes);
		} else {
			this.authorizedGrantTypes = new HashSet<String>(Arrays.asList(
					"authorization_code", "refresh_token"));
		}

		if (StringUtils.hasText(authorities)) {
			this.authorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList(authorities);
		}

		if (StringUtils.hasText(redirectUris)) {
			this.registeredRedirectUris = StringUtils
					.commaDelimitedListToSet(redirectUris);
		}
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setAutoApproveScopes(Collection<String> autoApproveScopes) {
		this.autoApproveScopes = new HashSet<String>(autoApproveScopes);
	}

	@Override
	public boolean isAutoApprove(String scope) {
		if (autoApproveScopes == null) {
			return false;
		}
		for (String auto : autoApproveScopes) {
			if (auto.equals("true") || scope.matches(auto)) {
				return true;
			}
		}
		return false;
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Set<String> getAutoApproveScopes() {
		return autoApproveScopes;
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public boolean isSecretRequired() {
		return this.clientSecret != null;
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public boolean isScoped() {
		return this.scope != null && !this.scope.isEmpty();
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Collection<String> scope) {
		this.scope = scope == null ? Collections.<String> emptySet()
				: new LinkedHashSet<String>(scope);
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Set<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(Collection<String> resourceIds) {
		this.resourceIds = resourceIds == null ? Collections
				.<String> emptySet() : new LinkedHashSet<String>(resourceIds);
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(Collection<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = new LinkedHashSet<String>(
				authorizedGrantTypes);
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Set<String> getRegisteredRedirectUri() {
		return registeredRedirectUris;
	}

	public void setRegisteredRedirectUri(Set<String> registeredRedirectUris) {
		this.registeredRedirectUris = registeredRedirectUris == null ? null
				: new LinkedHashSet<String>(registeredRedirectUris);
	}

	@org.codehaus.jackson.annotate.JsonProperty("authorities")
	@com.fasterxml.jackson.annotation.JsonProperty("authorities")
	private List<String> getAuthoritiesAsStrings() {
		return new ArrayList<String>(
				AuthorityUtils.authorityListToSet(authorities));
	}

	@org.codehaus.jackson.annotate.JsonProperty("authorities")
	@org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
	@com.fasterxml.jackson.annotation.JsonProperty("authorities")
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
	private void setAuthoritiesAsStrings(Set<String> values) {
		setAuthorities(AuthorityUtils.createAuthorityList(values
				.toArray(new String[values.size()])));
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public void setAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		this.authorities = new ArrayList<GrantedAuthority>(authorities);
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	@org.codehaus.jackson.annotate.JsonIgnore
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(
			Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public void setAdditionalInformation(Map<String, ?> additionalInformation) {
		this.additionalInformation = new LinkedHashMap<String, Object>(
				additionalInformation);
	}

	@org.codehaus.jackson.annotate.JsonAnyGetter
	@com.fasterxml.jackson.annotation.JsonAnyGetter
	public Map<String, Object> getAdditionalInformation() {
		return Collections.unmodifiableMap(this.additionalInformation);
	}

	@org.codehaus.jackson.annotate.JsonAnySetter
	@com.fasterxml.jackson.annotation.JsonAnySetter
	public void addAdditionalInformation(String key, Object value) {
		this.additionalInformation.put(key, value);
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((accessTokenValiditySeconds == null) ? 0
						: accessTokenValiditySeconds);
		result = prime
				* result
				+ ((refreshTokenValiditySeconds == null) ? 0
						: refreshTokenValiditySeconds);
		result = prime * result
				+ ((authorities == null) ? 0 : authorities.hashCode());
		result = prime
				* result
				+ ((authorizedGrantTypes == null) ? 0 : authorizedGrantTypes
						.hashCode());
		result = prime * result
				+ ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result
				+ ((clientSecret == null) ? 0 : clientSecret.hashCode());
		result = prime
				* result
				+ ((registeredRedirectUris == null) ? 0
						: registeredRedirectUris.hashCode());
		result = prime * result
				+ ((resourceIds == null) ? 0 : resourceIds.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		result = prime * result + ((additionalInformation == null) ? 0 : additionalInformation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseClient other = (BaseClient) obj;
		if (accessTokenValiditySeconds == null) {
			if (other.accessTokenValiditySeconds != null)
				return false;
		} else if (!accessTokenValiditySeconds.equals(other.accessTokenValiditySeconds))
			return false;
		if (refreshTokenValiditySeconds == null) {
			if (other.refreshTokenValiditySeconds != null)
				return false;
		} else if (!refreshTokenValiditySeconds.equals(other.refreshTokenValiditySeconds))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (authorizedGrantTypes == null) {
			if (other.authorizedGrantTypes != null)
				return false;
		} else if (!authorizedGrantTypes.equals(other.authorizedGrantTypes))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (clientSecret == null) {
			if (other.clientSecret != null)
				return false;
		} else if (!clientSecret.equals(other.clientSecret))
			return false;
		if (registeredRedirectUris == null) {
			if (other.registeredRedirectUris != null)
				return false;
		} else if (!registeredRedirectUris.equals(other.registeredRedirectUris))
			return false;
		if (resourceIds == null) {
			if (other.resourceIds != null)
				return false;
		} else if (!resourceIds.equals(other.resourceIds))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		if (additionalInformation == null) {
			if (other.additionalInformation != null)
				return false;
		} else if (!additionalInformation.equals(other.additionalInformation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OauthClient [clientId=" + clientId + ", clientSecret="
				+ clientSecret + ", scope=" + scope + ", resourceIds="
				+ resourceIds + ", authorizedGrantTypes="
				+ authorizedGrantTypes + ", registeredRedirectUris="
				+ registeredRedirectUris + ", authorities=" + authorities
				+ ", accessTokenValiditySeconds=" + accessTokenValiditySeconds
				+ ", refreshTokenValiditySeconds="
				+ refreshTokenValiditySeconds + ", additionalInformation="
				+ additionalInformation + "]";
	}
	
}

/**


客户端信息配置属性说明：
clientId：（必须的）第三方用户的id（可理解为账号）。
clientSecret：第三方应用和授权服务器之间的安全凭证(可理解为密码)
scope：指定客户端申请的权限范围,可选值包括read,write,trust;其实授权赋予第三方用户可以在资源服务器获取资源，第三方访问资源的一个权限，访问范围。
resourceIds：客户端所能访问的资源id集合
authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
可选值包括authorization_code,password,refresh_token,implicit,client_credentials
最常用的grant_type组合有: "authorization_code,refresh_token"(针对通过浏览器访问的客户端); "password,refresh_token"(针对移动设备的客户端)
registeredRedirectUris：客户端的重定向URI
autoApproveScopes：设置用户是否自动Approval操作, 默认值为 false,
可选值包括 true,false, read,write.
该字段只适用于grant_type="authorization_code的情况,当用户登录成功后,
若该值为true或支持的scope值,则会跳过用户Approve的页面, 直接授权.
authorities：指定客户端所拥有的Spring Security的权限值。
accessTokenValiditySeconds：设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
refreshTokenValiditySeconds：设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
additionalInformation：这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据

具体可参考：http://andaily.com/spring-oauth-server/db_table_description.html

 **/
