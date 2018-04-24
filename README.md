## 架构总结 ##
- 使用了**面向服务的架构**（Service-Oriented Architecture， SOA）<br>![](https://img-blog.csdn.net/20180423164420102)

### 数据库 ###
- mysql作为关系型数据库，提供了数据的持久化。
- redis作为内存数据库，提供高性能的缓存服务。


### 服务层 ###
- taotao-rest提供商品信息，内容服务。
- taotao-sso提供单点登录服务。
- taotao-order提供订单服务。
- taotao-search提供商品搜索服务，基于solr索引库。

### 交互 ###
- taotao-portal作为网站的门户，与用户交互。
- 微信商城之类的app也可以调用服务层的服务，完成门户的功能。
- taotao-manager作为后台管理，供管理员完成对网站商品和内容的增删改查。

## 开发过程总结 ##
1. [淘淘商城简介——淘淘商城（一）](https://blog.csdn.net/qq_40369829/article/details/79515237)：电商行业的了解和商城架构的分析。
2. [后台管理系统框架搭建——淘淘商城（二）](https://blog.csdn.net/qq_40369829/article/details/79515255——淘淘商城（二）)：使用IDEA结合maven创建多模块工程taotao-manager。
3. [SSM框架的整合——淘淘商城（三）](https://blog.csdn.net/qq_40369829/article/details/79515268)：对Mybatis、Spring、SpringMVC框架进行配置，实现整合。
4. [商品列表功能实现——淘淘商城（四）](https://blog.csdn.net/qq_40369829/article/details/79515278)：基于SSM框架，使用pagehelper插件和datagrid组件完成分页展示功能。
5. [商品类目选择——淘淘商城（五）](https://blog.csdn.net/qq_40369829/article/details/79515291)：使用easyui tree组件完成对商品类目的展示。
6. [图片的管理方式——淘淘商城（六）](https://blog.csdn.net/qq_40369829/article/details/79515349)：分析传统和使用图片服务器的管理方式。
7. [ubuntu上安装vsftpd——淘淘商城（七）](https://blog.csdn.net/qq_40369829/article/details/79515361)：在linux安装ftp服务器，接收上传的图片。
8. [ubuntu上安装nginx——淘淘商城（八）](https://blog.csdn.net/qq_40369829/article/details/79515383)：在linux安装nginx，作为http服务器，提供图片访问的服务。
9. [上传商品图片——淘淘商城（九）](https://blog.csdn.net/qq_40369829/article/details/79515562)：使用FTPClient和kindEditor上传图片到图片服务器。 
10. [添加商品功能——淘淘商城（十）](https://blog.csdn.net/qq_40369829/article/details/79515577)：编辑和将商品信息入库。
11. [商品规格的数据库设计和实现流程——淘淘商城（十一）](https://blog.csdn.net/qq_40369829/article/details/79515596)：分析使用多张表或是模板储存商品规格。
12. [商品规格管理的实现——淘淘商城（十二）](https://blog.csdn.net/qq_40369829/article/details/79515608)：使用模板法储存和展示商品规格。
13. [前台工程搭建——淘淘商城（十三）](https://blog.csdn.net/qq_40369829/article/details/79674013)：搭建门户工程taotao-portal和服务层工程之一taotao-rest。
14. [首页商品分类展示——淘淘商城（十四）](https://blog.csdn.net/qq_40369829/article/details/79674028)：jsonp使得ajax跨域调用服务层，展示商品分类。
15. [CMS系统开发——淘淘商城（十五）](https://blog.csdn.net/qq_40369829/article/details/79814698)：管理首页图片等内容。
16. [首页大广告展示——淘淘商城（十六）](https://blog.csdn.net/qq_40369829/article/details/79814842)：使用httpclient调用服务层，展示首页广告。
17. [redis简介和安装配置——淘淘商城（十七）](https://blog.csdn.net/qq_40369829/article/details/79824618)：redis的学习和安装。
18. [redis优化商城首页.——淘淘商城（十八）](https://blog.csdn.net/qq_40369829/article/details/79824676)：在业务中添加读取和写入缓存的逻辑，并且同步缓存。
19. [solr7.3在ubuntu14上的安装配置和使用——淘淘商城（十九）](https://blog.csdn.net/qq_40369829/article/details/79926799)：学习并安装和使用新版的solr服务器。
20. [基于solr7的搜索工程——淘淘商城（二十）](https://blog.csdn.net/qq_40369829/article/details/79927003)：搭建taotao-search，使用solrj导入商品索引并调用solr服务器进行搜索。
21. [商品搜索结果展示——淘淘商城（二十一）](https://blog.csdn.net/qq_40369829/article/details/79948146)：http调用服务层，展示搜索的商品。
22. [商品详情页面展示——淘淘商城（二十二）](https://blog.csdn.net/qq_40369829/article/details/79948164)：延时和按需加载商品内容，使用分层次的redis键。
23. [单点登录系统架构——淘淘商城（二十三）](https://blog.csdn.net/qq_40369829/article/details/80024572)：分析会话机制、session共享问题，了解单点登录。
24. [单点登录系统的接口开发——淘淘商城（二十四）](https://blog.csdn.net/qq_40369829/article/details/80024669)：搭建taotao-sso，开发用户相关接口并使用postman测试。
25. [用户注册登录退出功能的实现——淘淘商城（二十五）](https://blog.csdn.net/qq_40369829/article/details/80024973)：操作cookie，标识用户登录状态，并实现页面跳转和拦截器强制登录。
26. [购物车的实现——淘淘商城（二十六）](https://blog.csdn.net/qq_40369829/article/details/80047469)：在cookie中储存购物车，并在页面展示购物车商品。
27. [订单系统的实现——淘淘商城（二十七）](https://blog.csdn.net/qq_40369829/article/details/80054160)：创建taotao-order，开发订单接口，并在门户工程中调用，展示订单和提交订单。

## 部署 ##
- 需要多个服务器集群，参考：[https://blog.csdn.net/u012453843/article/details/73694543](https://blog.csdn.net/u012453843/article/details/73694543)

## 项目源码 ##
- [https://github.com/hanjg/taotao](https://github.com/hanjg/taotao)
