package com.wills.blog.service.impl;

import com.wills.blog.bean.Tag;
import com.wills.blog.dao.TagMapper;
import com.wills.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper mapper;

    @Override
    public List<Tag> getAll() {
        return mapper.selectAll();
    }

    @Override
    public int getAllCount() {
        return mapper.selectCount(null);
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Tag add(Tag tag) {
        mapper.insert(tag);
        Example example = new Example(Tag.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("tagName",tag.getTagName());
        List<Tag> list = mapper.selectByExample(example);
        if(list.size() !=0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
