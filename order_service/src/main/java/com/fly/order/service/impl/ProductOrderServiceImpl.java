package com.fly.order.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fly.order.domain.ProductOrder;
import com.fly.order.service.ProductClient;
import com.fly.order.service.ProductOrderService;
import com.fly.order.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {


    @Autowired
    private ProductClient productClient;


    @Autowired
    private RestTemplate restTemplate;


    public ProductOrder saveByRibbon(int userId, int productId) {

        if (userId == 0) {
            return null;
        }

        // Ribbon 调用方式
        Map<String, Object> productMap = restTemplate.getForObject("http://product-service/api/v1/product/find?id=" + productId, Map.class);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(productMap.get("name").toString());
        productOrder.setPrice(Integer.parseInt(productMap.get("price").toString()));
        productOrder.setId(new Random().nextInt(10000));
        return productOrder;
    }


    @Override
    public ProductOrder saveByFeign(int userId, int productId) {
        if (userId == 0) {
            return null;
        }
        //调用订单服务
        String response = productClient.findById(productId);
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        productOrder.setId(new Random().nextInt(10000));
        return productOrder;
    }


}
