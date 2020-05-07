package com.wills.blog.bean;

import lombok.Data;

@Data
public class ArticleCategory {

    private String name;

    private String value;

    public ArticleCategory(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
