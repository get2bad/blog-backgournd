package com.wills.blog.service;

import com.wills.blog.bean.Permission;
import com.wills.blog.bean.PermissonTotal;
import com.wills.blog.bean.WillsPageHelper;

import java.util.List;

public interface PermissionService {

    public void add(Permission permission);

    public void update(Permission permission);

    public void delete(Permission permission);

    public List<Permission> getAll(WillsPageHelper pageHelper);

    public int getAllCount();

    public  List<Permission> getRolePermission(int roleId);
}
