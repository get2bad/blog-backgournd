package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "role_user")
@ApiModel(value = "用户角色",description = "用户角色表")
@Data
public class RoleUser implements Serializable {

    @Id
    @ApiModelProperty(name = "userId")
    @Column(name = "user_id")
    private int userId;

    @Id
    @ApiModelProperty(name = "roleId")
    @Column(name = "role_id")
    private int roleId;

    public RoleUser(int userId) {
        this.userId = userId;
    }

    public RoleUser(int userId, int roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
