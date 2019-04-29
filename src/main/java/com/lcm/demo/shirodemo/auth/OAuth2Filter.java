package com.lcm.demo.shirodemo.auth;

import com.alibaba.fastjson.JSON;
import com.lcm.demo.shirodemo.common.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-28 15:49
 **/

public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        String token=getToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)){
            return null;
        }
        return new OAuth2Token(token);

    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            //httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            String json = JSON.toJSONString(Result.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));

            httpResponse.getWriter().print(json);

            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }

    private String getToken(HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)){
            token=httpServletRequest.getParameter("token");
        }
        return token;
    }

}
