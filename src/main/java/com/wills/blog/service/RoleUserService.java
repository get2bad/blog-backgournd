package com.wills.blog.service;

import com.wills.blog.bean.Role;

import java.util.List;

public interface RoleUserService {

    //public void add(RoleUser roleUser);

    public List<Role> getUserRole(int userId);

    //public void delete(RoleUser roleUser);

    public void update(int userId, List<Role> roleList);
}
