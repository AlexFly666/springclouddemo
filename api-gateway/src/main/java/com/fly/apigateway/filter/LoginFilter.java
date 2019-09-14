package com.fly.apigateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


/**
 * @Title: 登录过滤器
 * @ClassName: com.fly.apigateway.filter.LoginFilter.java
 * @Description:
 *
 * @Copyright 2016-2019 谐云科技 - Powered By 研发中心
 * @author: 王延飞
 * @date:  2019/9/14 12:22
 * @version V1.0
 */
@Component
public class LoginFilter  extends ZuulFilter {

    /**
     * 过滤器类型，前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器顺序，越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {

        return 4;
    }


    /**
     * 过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest  request = requestContext.getRequest();

        //ACL 配置
        if ("/apigateway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }else if ("/apigateway/order/api/v1/order/list".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }else if ("/apigateway/order/api/v1/order/find".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }

        return false;
    }

    /**
     * @Title: 具体业务逻辑
     * @MethodName:  run
     * @param
     * @Return java.lang.Object
     * @Exception
     * @Description: 这里模拟token验证
     *
     * @author: 王延飞
     * @date:  2019/9/14 11:26
     */
    @Override
    public Object run() throws ZuulException {

        //JWT
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest  request = requestContext.getRequest();

        //token对象
        String token = request.getHeader("token");

        if(StringUtils.isBlank((token))){
            token  = request.getParameter("token");
        }

        //登录校验逻辑  根据公司情况自定义 JWT
        if (StringUtils.isBlank(token)) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }



}
