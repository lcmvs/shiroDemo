package com.lcm.demo.shirodemo.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description: 最常见的principals和credentials组合就是用户名/密码了。
 * @author: lcm
 * @create: 2019-04-28 15:51
 **/

public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    /**
     * 身份
     * 即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。
     * 一个主体可以有多个principals，但只有一个Primary principals，
     * 一般是用户名/密码/手机号。
     * @return
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * 证明
     * 即只有主体知道的安全值，如密码/数字证书等。
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }

}
