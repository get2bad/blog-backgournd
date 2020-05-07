package com.wills.blog.dao;

import com.wills.blog.bean.Artical;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticalMapper extends tk.mybatis.mapper.common.Mapper<Artical> {

    @Update("update artical set status = (case `status` when 0 then 1 else 0 end) where artical = #{articalId}")
    public void changeStatus(@Param("articalId") int articalId);

    @Select("select sum(view_count) from artical")
    public int getAllView();

    @Select("select * from artical where category_id = #{id} AND status = 1 order by artical_id desc limit #{start},#{end}")
    public List<Artical> getByCategoryId(@Param("id") int id,@Param("start")int start,@Param("end")int end);

    @Select("select * from artical where status = 1")
    public List<Artical> getAll();

    @Select("select count(*) from artical where status = 1")
    public int getAllPassCount();
}
