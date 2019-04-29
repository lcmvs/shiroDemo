package com.lcm.demo.shirodemo.module.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SysRole {

    @Id
    private Long roleId;

    private Long roleName;

    private Byte status;

    private Date createTime;

    private Date updateTime;

}