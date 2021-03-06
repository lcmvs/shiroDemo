package com.lcm.demo.shirodemo.module.controller;

import com.lcm.demo.shirodemo.common.constant.Constant;
import com.lcm.demo.shirodemo.common.util.Result;
import com.lcm.demo.shirodemo.common.util.TokenUtil;
import com.lcm.demo.shirodemo.module.controller.requestBody.LoginBody;
import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import com.lcm.demo.shirodemo.module.service.TestService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class TestController extends AbstractCommonController {

    @RequiresPermissions("test")
    @PostMapping("/test")
    public Result test(){
        return Result.ok("test");
    }

    @RequiresPermissions("hehe")
    @PostMapping("/hehe")
    public Result hehe(){
        return Result.ok("hehe");
    }

}
