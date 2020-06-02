package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
@Table(name = "permission")
@ApiModel(value = "权限",description = "Permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    @Id
    @ApiModelProperty(name = "permission_id")
    @Column(name = "permission_id")
    private int permissionId;

    @ApiModelProperty(name = "permission_name")
    @Column(name = "permission_name")
    private String permissionName;

    @ApiModelProperty(name = "permission_description")
    @Column(name = "permission_description")
    private String permissionDescription;

    @ApiModelProperty(name = "level")
    @Column(name = "level")
    private int level;

    @ApiModelProperty(name = "parent_id")
    @Column(name = "parent_id")
    private int parentId;
    @Transient
    private List<Permission> child;

    public Permission(int permissionId) {
        this.permissionId = permissionId;
    }

    public Permission(int permissionId, String permissionName, String permissionDescription, int level, int parentId) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
        this.level = level;
        this.parentId = parentId;
    }
}
