package com.wills.blog.service;

import com.wills.blog.bean.*;

import java.util.List;

public interface UserService {

    // 获取所有用户数
    public int getTotalCount();

    //  获取用户列表
    public List<User> getAllUser(WillsPageHelper pageHelper);

    // 注册用户
    public void regist(User user);

    // 用户条件查询
    public User findOne(User user);

    // 修改用户资料操作
    public void updateUserData(UserInfo userInfo);

    //禁止用户登陆
    public void banUser(int id);

    //修改用户信息
    public void updateUser(User user);

    //重置密码操作
    public void resetPwd(int id,String newPwd);

    // 获得某个ID的用户信息
    public User getById(int id);

    // 通过用户名查询某个特定用户
    public User getByUserName(String userName);

    // 查询某个用户的角色
    public List<Role> getRoleListByUserId(int userId);

    // 查询某个角色的所有权限
    public List<Permission> getPermissionListByRoleId(int roleId);
}
