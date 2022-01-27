# quickTaxi
> 阿甘快车 基于spring-boot,spring-cloud ~~netflix组件(废弃)~~ 替换成alibaba

&nbsp;
### ~~eurekaServer *eurekaServer服务*~~
>~~需要提前开启eurekaServer服务才可运行其他服务模块~~
&nbsp;
### api *接口和网络传输协议*
>api为服务接口
> 遵循RESTful风格
> - post 新增
> - get /user/list 列出所有user
> - get /user/{id} 列出指定user
> - put /user/{id} 更新user全部信息
> - patch /user/{id} 更新user指定部分信息
> - delete /user/{id} 删除指定user

> dto 定义了request和response传输的协议
&nbsp;
### common *通用基本组建*
> 常量 工具类等通用基本组建
&nbsp;
### gateway *网关*
> 基于loadbalancer的网关
&nbsp;
### modules *服务模块*
 - **sms** *sms服务 负责邮件,短信,电话发送*
 - **user** *基础用户服务*
 

## 任务
- [x] 服务网关
- [ ] 单点登陆
    - [x] jwt
    - [x] 网关拦截
    - [x] 接口权限控制
    - [ ] 数据隔离
- [x] 配置中心
  -[x] nacos
- [x] 注册中心 
  - [x] nacos
- [x] 熔断降级
  - [x] sentinel
- [x] 系统接口
  - [x] swagger
  - [x] knife4j 
  - http://localhost/doc.html
- [ ] 分布式事务  
- [ ] 分布式日志
- [ ] 链路追踪


