package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@ApiModel(value = "角色信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @ApiModelProperty(name = "role_id")
    @Column(name = "role_id")
    private int roleId;

    @ApiModelProperty(name = "role_name")
    @Column(name = "role_name")
    private String roleName;

    @ApiModelProperty(name = "role_description")
    @Column(name = "role_description")
    private String roleDescription;

    private List<Permission> allPermission;

    public Role(int roleId) {
        this.roleId = roleId;
    }

    public Role(int roleId, String roleName, String roleDescription) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
}
