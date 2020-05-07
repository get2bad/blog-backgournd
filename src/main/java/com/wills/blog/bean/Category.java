package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@ApiModel(value = "分类信息")
@Data
public class Category implements Serializable {

    @Id
    @Column(name = "category_id")
    @ApiModelProperty(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    @ApiModelProperty(name = "category_name")
    private String categoryName;

    @Column(name = "category_url")
    @ApiModelProperty(name = "category_url")
    private String categoryUrl;

    @Column(name = "icon")
    @ApiModelProperty(name = "icon")
    private String icon;

    public Category(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category(int categoryId, String categoryName, String categoryUrl, String icon) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryUrl = categoryUrl;
        this.icon = icon;
    }
}
