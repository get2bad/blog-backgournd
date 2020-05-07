package com.wills.blog.service.impl;

import com.wills.blog.dao.ConfigMapper;
import com.wills.blog.bean.Config;
import com.wills.blog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper mapper;


    @Override
    public void add(Config config) {
        mapper.insert(config);
    }

    @Override
    public void update(Config config) {
        mapper.updateByPrimaryKey(config);
    }

    @Override
    public void delete(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Config> getAll() {
        return mapper.selectAll();
    }

    @Override
    public int getAllCount() {
        return mapper.selectCount(null);
    }
}
