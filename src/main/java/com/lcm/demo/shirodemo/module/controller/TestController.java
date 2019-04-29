package com.lcm.demo.shirodemo.module.controller;

import com.lcm.demo.shirodemo.common.constant.Constant;
import com.lcm.demo.shirodemo.common.util.Result;
import com.lcm.demo.shirodemo.common.util.TokenUtil;
import com.lcm.demo.shirodemo.module.controller.requestBody.LoginBody;
import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import com.lcm.demo.shirodemo.module.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-26 17:07
 **/
@RestController
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TestService testService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody() LoginBody body){
        SysUser user = testService.login(body.getUsername(),body.getPassword() );
        if (user==null){
            return Result.error("账号密码错误！");
        }
        String token= TokenUtil.getToken(user.getUserId());
        stringRedisTemplate.opsForValue().set(token,user.getUserId()+"", Constant.TOKEN_TIMEOUT, TimeUnit.SECONDS);
        return Result.ok().put("token",token);
    }

    @PostMapping("/test")
    public Result test(){
        return Result.ok("test");
    }

}
