package com.wills.blog.service.impl;

import com.wills.blog.dao.NoteMapper;
import com.wills.blog.bean.Note;
import com.wills.blog.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;


    @Override
    public List<Note> getAll() {
        return noteMapper.selectAll();
    }



    @Override
    public List<Note> getAll(int userId) {
        List<Note> all = noteMapper.selectByExample(opreate(userId));
        return all;
    }

    public Example opreate(int userId){
        Example example = new Example(Note.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("userId",userId);
        return example;
    }

    @Override
    public void add(Note note) {
        noteMapper.insert(note);
    }

    @Override
    public void delete(int noteId) {
        noteMapper.deleteByExample(opreate(noteId));
    }

    @Override
    public void update(Note note) {
        noteMapper.updateByPrimaryKey(note);
    }

    @Override
    public int getAllCount() {
        return noteMapper.selectCount(null);
    }
}
