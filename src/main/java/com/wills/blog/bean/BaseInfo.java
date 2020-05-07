package com.wills.blog.bean;

import lombok.Data;

@Data
public class BaseInfo {

    private String baseName1;

    private String baseVal1;

    private String baseName2;

    private String baseVal2;

    public BaseInfo(String baseName1, String baseVal1, String baseName2, String baseVal2) {
        this.baseName1 = baseName1;
        this.baseVal1 = baseVal1;
        this.baseName2 = baseName2;
        this.baseVal2 = baseVal2;
    }
}
