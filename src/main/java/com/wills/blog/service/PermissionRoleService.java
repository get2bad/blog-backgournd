package com.wills.blog.service;

import com.wills.blog.bean.Permission;
import com.wills.blog.bean.PermissionRole;

import java.util.List;

public interface PermissionRoleService {

    public void operate(int roleId, List<Integer> all);

    public List<Permission> getAll(int roleId);

    public int getAllCount(int roleId);
}
