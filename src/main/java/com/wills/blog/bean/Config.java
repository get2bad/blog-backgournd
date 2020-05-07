package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "config")
@ApiModel(value = "配置信息")
@Data
public class Config  implements Serializable {

    @Id
    @ApiModelProperty("id")
    @Column(name = "id")
    private int id;

    @ApiModelProperty("config_name")
    @Column(name = "config_name")
    private String configName;

    @ApiModelProperty("config_content")
    @Column(name = "config_content")
    private String configContent;

    @ApiModelProperty(value = "config_type",notes = "配置项的类型，默认为text")
    @Column(name = "config_type")
    private String configType;

    @ApiModelProperty(value = "config_if_select",notes = "配置项是否是可以选择或者打钩的类型,如果是则内容就是json格式的可选项")
    @Column(name = "config_if_select")
    private String configIfSelect;

    @ApiModelProperty(value = "config_if_multify",notes = "配置项是否含有备选项，0没有1有")
    @Column(name = "config_if_multify")
    private int configIfMultify;

    public Config(int id, String configName, String configContent, String configType, String configIfSelect, int configIfMultify) {
        this.id = id;
        this.configName = configName;
        this.configContent = configContent;
        this.configType = configType;
        this.configIfSelect = configIfSelect;
        this.configIfMultify = configIfMultify;
    }
}
