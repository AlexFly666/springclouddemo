package com.fly.order.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * @Title: json工具类
 * @ClassName: com.fly.order.utils.JsonUtils.java
 * @Description:
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:29
 * @version V1.0
 */
public class JsonUtils {


    private static final ObjectMapper objectMappper = new ObjectMapper();


    /**
     * json字符串转JsonNode对象的方法
     */
    public static JsonNode str2JsonNode(String str){
        try {
            return  objectMappper.readTree(str);
        } catch (IOException e) {
            return null;
        }
    }



}
