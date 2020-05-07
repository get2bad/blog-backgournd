package com.wills.blog.dao;

import com.wills.blog.bean.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends tk.mybatis.mapper.common.Mapper<Tag> {
}
