package com.fly.order.fallbcak;

import com.fly.order.service.ProductClient;
import org.springframework.stereotype.Component;


/**
 * @Title: 针对商品服务错误，降级处理
 * @ClassName: com.fly.order.fallbcak.ProductClientFallback.java
 * @Description:
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:28
 * @version V1.0
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {

        System.out.println("feign 调用product-service findbyid 异常");

        return null;
    }



}
