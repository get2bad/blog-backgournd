package com.wills.blog.service;

import com.wills.blog.bean.Config;

import java.util.List;

public interface ConfigService {

    public void add(Config config);

    public void update(Config config);

    public void delete(int id);

    public List<Config> getAll();

    public int getAllCount();
}
