package com.wills.blog.dao;

import com.wills.blog.bean.PermissionRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionRoleMapper extends tk.mybatis.mapper.common.Mapper<PermissionRole> {

    @Delete("delete from permission_role where role_id = #{roleId}")
    public void deleteByRoleId(@Param("roleId") int roleId);
}
