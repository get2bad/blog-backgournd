package com.wills.blog;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class SimpleTest {

    @Test
    public void test1(){
        String reg = "/system/[^\\s]*";
        System.out.println(Pattern.matches(reg,"/system/disk"));
    }
}
