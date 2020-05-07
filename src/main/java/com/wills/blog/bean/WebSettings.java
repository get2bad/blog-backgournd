package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "websettings")
@ApiModel(value = "网站的配置信息")
@Data
public class WebSettings {

    @Id
    @ApiModelProperty("id")
    @Column(name = "id")
    private int id;

    @ApiModelProperty("web_key")
    @Column(name = "web_key")
    private String webKey;

    @ApiModelProperty("web_value")
    @Column(name = "web_value")
    private String webValue;

    @ApiModelProperty("description")
    @Column(name = "description")
    private String description;
}
