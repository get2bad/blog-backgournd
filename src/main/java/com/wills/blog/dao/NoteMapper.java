package com.wills.blog.dao;

import com.wills.blog.bean.Note;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteMapper extends tk.mybatis.mapper.common.Mapper<Note> {
}
