package com.fly.order.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @Title: 商品订单实体类
 * @ClassName: com.fly.order.domain.ProductOrder.java
 * @Description:
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:25
 * @version V1.0
 */
public class ProductOrder implements Serializable {

    /**
     * 订单ID
     */
    private int id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 订单号
     */
    private  String tradeNo;

    /**
     * 价格,分
     */
    private int price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户ID
     */
    private int userId;
    /**
     *  用户姓名，默认：FLY
     */
    private String userName = "FLY";


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
