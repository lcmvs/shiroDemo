package com.lcm.demo.shirodemo.module.service;

import com.lcm.demo.shirodemo.module.dao.entity.SysUser;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-26 17:06
 **/
public interface TestService {

    SysUser login(String username, String password);

}
