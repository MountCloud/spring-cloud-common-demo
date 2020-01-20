# Introduction
   This demo uses https://github.com/MountCloud/spring-cloud-common-parent, which includes service registration, Oauth2 authentication, Config server configuration management, and uses MongoDB, Mybatis in spring-cloud-common-parent , Redis. The purpose of this demo is to use spring-cloud-common-parent better and faster.
  
# Content
testconfigserver: Config server.

testgatewayserver: is the gateway service.

testoauthserver: is oauth2 service. Which demonstrates the use of Redis and Mongodb.

testuserservice: test user service. Which demonstrates the use of Mybatis.

Specific content can enter the project to view their own Readme file.

# Run DEMO
   You need to prepare the following tools:
  
   Minimum Jdk8, maven, redis, mysql, mongodb, consul, postman.
  
   You still need to go to src / main / resources / config in tesconfigserver to confirm whether your environment is consistent with the configuration in DEMO.

# Test DEMO
   Open postman, import [spring cloud demo.postman_collection.json] json into postman, and test the interface according to your actual situation.

# 简介
  这个demo使用了https://github.com/MountCloud/spring-cloud-common-parent ，包含了服务注册、Oauth2鉴权、Config server配置管理，使用了spring-cloud-common-parent中的MongoDB、Mybatis、Redis。这个demo的目的是为了更好更快的去使用spring-cloud-common-parent。
  
# 内容
testconfigserver：是Config server。

testgatewayserver：是网关服务。

testoauthserver：是oauth2服务。其中演示了Redis和Mongodb的使用。

testuserservice：是测试的用户服务。其中演示了Mybatis的使用。

具体内容可以进入项目中查看他们自己的Readme文件。

# 运行DEMO
  你需要准备以下工具：
  
  最低Jdk8、maven、redis、mysql、mongodb、consul、postman。
  
  你仍然需要进入tesconfigserver中src/main/resources/config中确认你的环境与DEMO中的配置是否一致。

# 测试DEMO
  打开postman，将[spring cloud demo.postman_collection.json]json导入postman，并且根据自己的实际情况测试接口。
