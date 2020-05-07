package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@ApiModel(value = "后台首页的导航栏")
@Table(name = "base")
@Data
public class Base implements Serializable {

    @Id
    @ApiModelProperty(value = "id")
    @Column(name = "id")
    private int id;

    @ApiModelProperty(value = "base_name")
    @Column(name = "base_name")
    private String baseName;

    @ApiModelProperty(value = "url")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(value = "icon")
    @Column(name = "icon")
    private String icon;

    @ApiModelProperty(value = "parent_id")
    @Column(name = "parent_id")
    private int parentId;

    @Transient
    private List<Base> children;

}
