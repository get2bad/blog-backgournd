package com.wills.blog.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Captcha {

    @Autowired
    private Environment env;

    private static String appId;

    private static String appSecret;

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        Captcha.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        Captcha.appSecret = appSecret;
    }

    @PostConstruct
    public void init(){
        appId = env.getProperty("wills.captcha.appId");
        appSecret = env.getProperty("wills.captcha.appSecret");
    }
}
