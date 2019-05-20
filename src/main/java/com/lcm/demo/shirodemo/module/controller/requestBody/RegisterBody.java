package com.lcm.demo.shirodemo.module.controller.requestBody;

import lombok.Data;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-30 09:31
 **/
@Data
public class RegisterBody {

    private String realName;

    private String username;

    private String password;

    private String phone;

}
