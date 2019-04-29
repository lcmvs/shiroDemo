package com.lcm.demo.shirodemo.module.dao;

import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-28 10:28
 **/
public interface SysUserDao extends JpaRepository<SysUser,Long> {

    SysUser findFirstByUsername(String name);

}
