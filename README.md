# quickTaxi
> 快车项目 基于spring-cloud netflix组件

&nbsp;
### eurekaServer *eurekaServer服务*
>需要提前开启eurekaServer服务才可运行其他服务模块
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
 

