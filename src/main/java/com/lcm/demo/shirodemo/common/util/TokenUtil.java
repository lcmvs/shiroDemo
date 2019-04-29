package com.lcm.demo.shirodemo.common.util;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-28 10:37
 **/

public class TokenUtil {

    private static final String SALT="TGf8@Ay@D$YElqhA%fRcauybjbAcFbKw";

    /**
     * 生成32位md5token
     * @param id
     * @return
     */
    public static String createToken(Long id){
        String str=UUID.randomUUID()+SALT+id;
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static void main(String[] args){
        System.out.println(createToken(1L));
    }

}
