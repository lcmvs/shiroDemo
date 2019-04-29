package com.lcm.demo.shirodemo.module.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SysPermission {

    @Id
    private Long permissionId;

    private String permissionName;

    private Byte status;

    private Date createTime;

    private Date updateTime;

}