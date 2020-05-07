package com.wills.blog.util;

import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;
import com.wills.blog.bean.Captcha;
import org.springframework.stereotype.Component;

@Component
public class CaptchaUtil {

    public static Boolean verifyToken(String token) throws Exception {
        CaptchaClient c = new CaptchaClient(Captcha.getAppId(),Captcha.getAppSecret());
        CaptchaResponse res = c.verifyToken(token);
        return res.getResult();
    }
}
