package com.lcm.demo.shirodemo.module.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class SysUser {

    @Id
    private Long userId;

    private String realName;

    private String username;

    private String password;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    @Transient
    private Set<String> permissions;

}