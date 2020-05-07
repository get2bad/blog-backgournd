package com.wills.blog.service.impl;

import com.wills.blog.dao.ConstructMapper;
import com.wills.blog.bean.Construct;
import com.wills.blog.service.ConstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConstructServiceImpl implements ConstructService {

    @Autowired
    private ConstructMapper mapper;

    @Override
    public void add(Construct construct) {
        mapper.insert(construct);
    }

    @Override
    public void update(Construct construct) {
        mapper.updateByPrimaryKey(construct);
    }

    @Override
    public void delete(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Construct> getAll() {
        return mapper.selectAll();
    }

    @Override
    public int getAllCount() {
        return mapper.selectCount(null);
    }
}
