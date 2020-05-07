package com.wills.blog.service;

import com.wills.blog.bean.Note;

import java.util.List;

public interface NoteService {

    public List<Note> getAll();

    public List<Note> getAll(int userId);

    public void add(Note note);

    public void delete(int noteId);

    public void update(Note note);

    public int getAllCount();

}
