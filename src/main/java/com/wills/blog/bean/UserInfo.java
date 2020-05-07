package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@ApiModel(value = "用户信息",description = "user的详细信息")
@Data
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "user_id")
    @Column(name = "user_id")
    @Id
    private int userId;
    @ApiModelProperty(value = "sex")
    @Column(name = "sex")
    private int sex;
    @ApiModelProperty(value = "age")
    @Column(name = "age")
    private int age;
    @ApiModelProperty(value = "phone")
    @Column(name = "phone")
    private String phone;
    @ApiModelProperty(value = "email")
    @Column(name = "email")
    private String email;
    @ApiModelProperty(value = "address")
    @Column(name = "address")
    private String address;
    @ApiModelProperty(value = "note")
    @Column(name = "note")
    private String note;
}
