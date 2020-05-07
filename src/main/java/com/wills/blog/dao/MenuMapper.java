package com.wills.blog.dao;

import com.wills.blog.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends tk.mybatis.mapper.common.Mapper<Menu> {

    @Select("select * from menu")
    public List<Menu> getAllMenu();
}
