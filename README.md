# ums

一个基于Spring boot、thymeleaf、mybatis、pagehelper、druid、mysql、layui开发的基础用户权限管理项目

## 环境依赖
java 8+

mysql 8.0+

gradle 5.0+


## 使用步骤
1、使用test包里面的ums.sql初始化数据库与表

2、如果需要加表或者修改字段，修改mybatis-generator.xml,使用测试代码生成新的实体与mapper

3、使用UmsApplication启动...