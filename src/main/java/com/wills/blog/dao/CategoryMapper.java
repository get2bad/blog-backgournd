package com.wills.blog.dao;

import com.wills.blog.bean.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends tk.mybatis.mapper.common.Mapper<Category> {
}
