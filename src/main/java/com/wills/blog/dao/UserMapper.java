package com.wills.blog.dao;

import com.wills.blog.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    @Update("update user set status = ( case `status` when 0 then 1 else 0 end) where user_id = #{id};")
    public void banUser(@Param("id") int id);

    @Update("update user set password = #{newPwd} where user_id = #{id}")
    public void resetPwd(@Param("id") int id,@Param("newPwd") String newPwd);
}
