package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "construct")
@ApiModel(value = "结构")
@Data
public class Construct  implements Serializable {

    @Id
    @ApiModelProperty(name = "id")
    @Column(name = "id")
    private int id;

    @ApiModelProperty(name = "construct_name")
    @Column(name = "construct_name")
    private String constructName;

    @ApiModelProperty(name = "construct_url")
    @Column(name = "construct_url")
    private String constructUrl;

    @ApiModelProperty(name = "level")
    @Column(name = "level")
    private int level;

    @ApiModelProperty(name = "parent_id")
    @Column(name = "parent_id")
    private int parentId;

    @ApiModelProperty(name = "description")
    @Column(name = "description")
    private String description;

    public Construct(int id, String constructName, String constructUrl, int level, int parentId, String description) {
        this.id = id;
        this.constructName = constructName;
        this.constructUrl = constructUrl;
        this.level = level;
        this.parentId = parentId;
        this.description = description;
    }
}
