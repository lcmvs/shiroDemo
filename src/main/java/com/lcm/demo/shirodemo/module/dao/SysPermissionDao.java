package com.lcm.demo.shirodemo.module.dao;

import com.lcm.demo.shirodemo.module.dao.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-28 14:57
 **/
public interface SysPermissionDao extends JpaRepository<SysPermission,Long> {

    @Query(value="select a.permission_name from sys_permission a \n" +
            "inner join sys_role_permission b on a.permission_id=b.permission_id \n" +
            "inner join sys_user_role c on  b.role_id=c.role_id\n" +
            "where c.user_id=?1",nativeQuery = true)
    Set<String> getUserPermissions(long userId);

}
