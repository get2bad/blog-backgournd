package com.wills.blog.service;

import com.wills.blog.bean.Construct;

import java.util.List;

public interface ConstructService {

    public void add(Construct construct);

    public void update(Construct construct);

    public void delete(int id);

    public List<Construct> getAll();

    public int getAllCount();
}
