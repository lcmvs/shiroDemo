package com.lcm.demo.shirodemo.module.controller;

import com.lcm.demo.shirodemo.common.util.Result;
import com.lcm.demo.shirodemo.common.util.TokenUtil;
import com.lcm.demo.shirodemo.module.controller.requestBody.LoginBody;
import com.lcm.demo.shirodemo.module.controller.requestBody.RegisterBody;
import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import com.lcm.demo.shirodemo.module.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-30 09:26
 **/
@RestController
public class RegisterLoginController extends AbstractCommonController  {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TestService testService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody() LoginBody body){

        SysUser user = testService.login(body.getUsername(),body.getPassword());
        if (user==null){
            return Result.error("账号密码错误！");
        }

        String token= TokenUtil.createToken(user.getUserId());
        createToken(user.getUserId(),token);

        return Result.ok("登录成功").put("token",token);
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody() RegisterBody body){

        return Result.ok("注册成功！");
    }
}
