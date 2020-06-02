package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "permission_role")
@ApiModel(value = "角色权限")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRole implements Serializable {

    @Id
    @ApiModelProperty("permission_id")
    @Column(name = "permission_id")
    private int permissionId;

    @Id
    @ApiModelProperty("role_id")
    @Column(name = "role_id")
    private int roleId;

    public PermissionRole(int roleId) {
        this.roleId = roleId;
    }

}
