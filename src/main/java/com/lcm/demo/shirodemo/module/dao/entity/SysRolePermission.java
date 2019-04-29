package com.lcm.demo.shirodemo.module.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SysRolePermission {

    @Id
    private Long rolePermissionId;

    private Long roleId;

    private Long permissionId;

    private Byte status;

    private Date createTime;

    private Date updateTime;

}