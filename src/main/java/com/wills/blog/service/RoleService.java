package com.wills.blog.service;

import com.wills.blog.bean.Role;
import com.wills.blog.bean.RoleTotal;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public int getRolesCount();

    public void add(Role role);

    public void delete(Role role);

    public void update(Role role);

    public List<Role> getUserRole(int userId);
}
