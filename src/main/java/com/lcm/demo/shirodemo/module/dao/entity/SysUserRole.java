package com.lcm.demo.shirodemo.module.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserRole {

    private Long userRoleId;

    private Long userId;

    private Long roleId;

    private Byte status;

    private Date createTime;

    private Date updateTime;

}