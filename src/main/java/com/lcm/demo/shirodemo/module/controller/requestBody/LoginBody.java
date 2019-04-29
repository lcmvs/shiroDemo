package com.lcm.demo.shirodemo.module.controller.requestBody;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-29 09:22
 **/
@Data
public class LoginBody {

    @NotBlank(message = "账号为空")
    private String username;

    @NotBlank(message = "密码为空")
    private String password;

}
