package com.fly.order.service;

import com.fly.order.fallbcak.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Title: 商品服务客户端
 * @ClassName: com.fly.order.service.ProductClient.java
 * @Description: FeignClient结合Hystrix
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:28
 * @version V1.0
 */
@FeignClient(name = "product-service", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/api/v1/product/find")
    String findById(@RequestParam(value = "id") int id);

}
