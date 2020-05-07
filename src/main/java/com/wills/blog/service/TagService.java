package com.wills.blog.service;

import com.wills.blog.bean.Tag;

import java.util.List;

public interface TagService {

    public List<Tag> getAll();

    public int getAllCount();

    public void deleteById(int id);

    public Tag add(Tag tag);
}
