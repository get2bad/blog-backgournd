package com.wills.blog.bean;

import lombok.Data;

import java.util.List;
@Data
public class RoleTotal {

    private Role role;
    private List<Permission> permissionList;

    public RoleTotal(Role role, List<Permission> permissionList) {
        this.role = role;
        this.permissionList = permissionList;
    }
}
