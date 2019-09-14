package com.fly.order.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.fly.order.service.ProductOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Title: 订单接口
 * @ClassName: com.fly.order.controller.OrderController.java
 * @Description:
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:24
 * @version V1.0
 */
@RestController
@RequestMapping("api/v1/order")
public class OrderController {


    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;


    /**
     * @Title: 订单创建
     * @MethodName: save
     * @param userId  用户ID
     * @param productId 商品ID
     * @Return java.lang.Object
     * @Exception
     * @Description:
     *
     * @author: 王延飞
     * @date: 2019/9/14 10:00
     */
    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId) {

        Map<String, Object> data = new HashMap<>();

        // 模拟程序异常
        // int i = 1 / 0;
        data.put("code", 0);
        // Feign调用方式
        data.put("data", productOrderService.saveByFeign(userId, productId));
        //  Ribbon调用方式
        //  data.put("data", productOrderService.saveByRibbon(userId, productId));
        return data;

    }


    /**
     * @Title: Hystrix熔断、限流
     * @MethodName: saveOrderFail
     * @param userId
     * @param productId
     * @Return java.lang.Object
     * @Exception
     * @Description:
     *     注意，方法签名一定要要和api方法一致，参数也要一致
     *     可以通过下线商品服务；或者接口模拟异常 int i=1/0;
     *
     * @author: 王延飞
     * @date: 2019/9/14 9:54
     */
    private Object saveOrderFail(int userId, int productId) {

        //监控报警
        String saveOrderKye = "save-order";
        // 从redis查看是否已经发送过报警
        String sendValue = redisTemplate.opsForValue().get(saveOrderKye);
        // 模拟客户端IP
        final String ip = request.getRemoteAddr();

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("SaveOrderFail-pool-%d").build();
        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        pool.execute(() -> {
            if (StringUtils.isBlank(sendValue)) {
                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是=" + ip);
                //生产环境：需要发送一个http请求，调用短信服务
                redisTemplate.opsForValue().set(saveOrderKye, "save-order-fail", 20, TimeUnit.SECONDS);
            } else {
                System.out.println("已经发送过短信，20秒内不重复发送");
            }
        });

        // 返回值
        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "抢购人数太多，您被挤出来了，稍等重试");
        return msg;


    }
}
