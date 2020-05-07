package com.wills.blog.util;

import java.util.UUID;

public class ActiveID {

    public static String getRandomUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
