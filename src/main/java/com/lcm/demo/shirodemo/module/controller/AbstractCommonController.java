package com.lcm.demo.shirodemo.module.controller;

import com.lcm.demo.shirodemo.common.constant.Constant;
import com.lcm.demo.shirodemo.common.constant.RedisKey;
import com.lcm.demo.shirodemo.common.exception.GlobalException;
import com.lcm.demo.shirodemo.common.util.HttpContextUtils;
import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Description:controller公共组件
 *
 * @author lcm
 * @date 2019/3/16 19:50
 */
@Component
public abstract class AbstractCommonController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected String getTokenStr() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        // 获取用户凭证
        String token = request.getHeader(Constant.TOKEN);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(Constant.TOKEN);
        }

        // 凭证为空
        if (StringUtils.isBlank(token)) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED.value(),Constant.TOKEN + "不能为空");
        }

        return token;
    }

    protected void deleteToken(Long id){
        String token=stringRedisTemplate.opsForValue().get(RedisKey.SHIRO_ID+id);
        stringRedisTemplate.delete(RedisKey.SHIRO_ID+id);
        stringRedisTemplate.delete(RedisKey.SHIRO_TOKEN+token);
    }

    protected void createToken(Long id,String token){
        deleteToken(id);
        stringRedisTemplate.opsForValue().set(RedisKey.SHIRO_ID+id,token,Constant.TOKEN_TIMEOUT, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(RedisKey.SHIRO_TOKEN+token,id+"",Constant.TOKEN_TIMEOUT, TimeUnit.SECONDS);
    }

}
