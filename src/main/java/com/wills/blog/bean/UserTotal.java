package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "用户全部信息的集合",description = "用户注册主体类")
@Data
public class UserTotal implements Serializable {
    @ApiModelProperty(name = "user")
    private User user;
    @ApiModelProperty(name = "userInfo")
    private UserInfo userInfo;

    public UserTotal(User user, UserInfo userInfo) {
        this.user = user;
        this.userInfo = userInfo;
    }
}
