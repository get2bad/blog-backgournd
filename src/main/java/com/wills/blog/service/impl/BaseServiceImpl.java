package com.wills.blog.service.impl;

import com.wills.blog.bean.Base;
import com.wills.blog.dao.BaseMapper;
import com.wills.blog.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseMapper mapper;

    @Override
    public List<Base> getAll() {
        List<Base> real = mapper.selectByExample(getCondition("parent_id = 0"));
        List<Base> children = mapper.selectByExample(getCondition("parent_id <> 0"));
        for (Base child : children) {
            for (Base base : real) {
                if(child.getParentId() == base.getId()){
                    if(base.getChildren() == null) {
                        List<Base> list = new ArrayList<>();
                        list.add(child);
                        base.setChildren(list);
                    } else {
                        base.getChildren().add(child);
                    }
                }
            }
        }
        return real;
    }

    public Example getCondition(String condition){
        Example example = new Example(Base.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition(condition);
        return example;
    }
}
