package com.fly.product.domain;

import java.io.Serializable;
/**
 * @Title: 商品实体
 * @ClassName: com.fly.product.domain.Product.java
 * @Description:
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:31
 * @version V1.0
 */
public class Product implements Serializable {

    public  Product(){ }

    public  Product(int id, String name, int price, int store){
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
    }

    private int id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格,分为单位
     */
    private int price;

    /**
     * 库存
     */
    private int store;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", store=" + store +
                '}';
    }
}
