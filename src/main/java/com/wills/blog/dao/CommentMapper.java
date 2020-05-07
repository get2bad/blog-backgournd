package com.wills.blog.dao;

import com.wills.blog.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentMapper extends tk.mybatis.mapper.common.Mapper<Comment> {

    @Update("update comment set status = (case `status` when 0 then 1 else 0 end) where comment_id = #{id}")
    public void changeStatus(@Param("id") int id);

    @Select("select count(*) from comment where article_id = #{id}")
    public int articleCount(@Param("id") int articleId);

}
