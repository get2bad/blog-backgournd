package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tag")
@ApiModel(value = "文章的标签")
@Data
@AllArgsConstructor
public class Tag {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private int id;

    @Column(name = "tag_name")
    @ApiModelProperty(value = "tag_name")
    private String tagName;

    @Column(name = "tag_type")
    @ApiModelProperty(value = "tag_type")
    private String tagType;

}
