package com.lcm.demo.shirodemo.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @description: 加密算法
 * @author: lcm
 * @create: 2019-04-29 17:50
 **/

public class SecureHashUtil {

    /**
     * 加密算法
     */
    public final static String HASH_ALGORITHM_NAME = "SHA-256";
    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = 16;

    private final static String salt="0lakWzU0%8CQr18^Ukf@W6zoI*!F6l5b";

    public static String sha256(String password) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, getSalt(), HASH_ITERATIONS).toString();
    }

    public static String getSalt(){
        return DigestUtils.md5DigestAsHex((UUID.randomUUID()+salt).getBytes());
    }

    public static String getSalt(String str){
        return DigestUtils.md5DigestAsHex((UUID.randomUUID()+salt+str).getBytes());
    }

    public static void main(String[] args){
        System.out.println(sha256("123"));
    }

}
