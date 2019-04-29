package com.lcm.demo.shirodemo;

import com.lcm.demo.shirodemo.module.dao.SysPermissionDao;
import com.lcm.demo.shirodemo.module.dao.SysUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroDemoApplicationTests {

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysPermissionDao sysPermissionDao;

    @Test
    public void contextLoads() {
        //System.out.println(sysUserDao.findFirstByUsername("0"));
        System.out.println(sysPermissionDao.getUserPermissions(1L));
    }

}
