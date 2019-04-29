package com.lcm.demo.shirodemo.module.service.impl;

import com.lcm.demo.shirodemo.module.dao.SysUserDao;
import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import com.lcm.demo.shirodemo.module.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-28 16:32
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public SysUser login(String username, String password) {
        return sysUserDao.findFirstByUsername(username);
    }

}
