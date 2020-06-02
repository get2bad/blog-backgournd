package com.wills.blog.dao;

import com.wills.blog.bean.Log;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface LogDao extends Mapper<Log> {

    @Select("select * from log order by log_operationDate desc limit #{start}, #{end}")
    public List<Log> getAll(@Param("start") int start,@Param("end") int end);
}
