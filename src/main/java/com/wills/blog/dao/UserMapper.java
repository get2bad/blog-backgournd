package com.wills.blog.dao;

import com.wills.blog.bean.Permission;
import com.wills.blog.bean.Role;
import com.wills.blog.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    @Update("update user set status = ( case `status` when 0 then 1 else 0 end) where user_id = #{id};")
    public void banUser(@Param("id") int id);

    @Update("update user set password = #{newPwd} where user_id = #{id}")
    public void resetPwd(@Param("id") int id,@Param("newPwd") String newPwd);

    @Select("select * from role_user a left join role b on a.role_id=b.role_id where a.user_id=#{userId}")
    public List<Role> getRoleListByUserId(@Param("userId") int userId);

    @Select("select * from permission_role a left join permission b on a.permission_id=b.permission_id where a.role_id=#{roleId}")
    public List<Permission> getPermissionListByRoleId(@Param("roleId") int roleId);
}
