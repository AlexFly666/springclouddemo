## 访问地址
包含eureka注册中心，订单服务和商品服务（feign,ribbon）,hystrix统一监控中心，springgateway统一网关 
- __springgateway统一网关__:

  - [SimpleExample](  http://localhost:9000/apigateway/order/api/v1/order/save?product_id=5&user_id=5&token=123
):
  统一网关：有token检验机制，和限流


- __hystrix统一监控中心__:
  - [访问入口](http://localhost:8781/hystrix): 
    Hystrix Dashboard输入： http://localhost:8781/actuator/hystrix.stream 
    
- __订单服务和商品服务（feign,ribbon）__:
  - Ribbon：调用方式使用RestTemplate
  - Feign：调用方式使用@FeignClient，并结合Hystrix使用。
  如：@FeignClient(name = "product-service", fallback = ProductClientFallback.class)

- __eureka注册中心高可用__:

   修改host配置
 
    linux系统通过vim /etc/hosts 修改
    
    windows在C:\Windows\System32\drivers\etc\hosts 修改
    
    添加配置：
    
    ```
    127.0.0.1 eureka1
    127.0.0.1 eureka2
    127.0.0.1 eureka3
    ```
  - [eureka注册中心1](http://localhost:8761/)
  - [eureka注册中心2](http://localhost:8762/)
  - [eureka注册中心3](http://localhost:8763/)
  
  eureka高可用，采用3个节点；由于项目配置一样，这里放在一个项目里，通过 application.yml中的profiles区分。
  ```
    spring:
      profiles:
    #    active: eureka1
         active: eureka2
    #     active: eureka3
    
  ```
  
  
  通过：修改eureka服务端和客户端配置，实现了在service instace 挂掉15s, eureka能够感知到

  ```
    正常上线下线客户端最大感知时间：
    eureka.client.registryFetchIntervalSeconds+ribbon. ServerListRefreshInterval = 5秒
    
    异常下线客户端最大感知时间：
    2*eureka.instance.leaseExpirationDurationInSeconds+
    eureka.server.evictionIntervalTimerInMs+
    eureka.client.registryFetchIntervalSeconds+
    ribbon. ServerListRefreshInterval = 15秒
 ```