package com.fly.order.service;

import com.fly.order.domain.ProductOrder;

/**
 * @Title: 订单业务类
 * @ClassName: com.fly.order.service.ProductOrderService.java
 * @Description:
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:29
 * @version V1.0
 */
public interface ProductOrderService {


    /**
     * 下单接口(ByRibbon)
     * @param userId
     * @param productId
     * @return
     */
     ProductOrder saveByRibbon(int userId, int productId);


    /**
     * 下单接口(ByFeign)
     * @param userId
     * @param productId
     * @return
     */
    ProductOrder saveByFeign(int userId, int productId);


}
