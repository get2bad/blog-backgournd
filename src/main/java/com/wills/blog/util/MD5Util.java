package com.wills.blog.util;


import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String encrypt(String oldPwd){
        return DigestUtils.md5DigestAsHex(oldPwd.getBytes());
    }
}
