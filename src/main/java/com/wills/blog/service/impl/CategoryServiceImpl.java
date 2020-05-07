package com.wills.blog.service.impl;

import com.wills.blog.dao.CategoryMapper;
import com.wills.blog.bean.Category;
import com.wills.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int categoryId) {
        categoryMapper.deleteByExample(opearte(categoryId));
    }

    public Example opearte(int categoryId){
        Example example = new Example(Category.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("categoryId",categoryId);
        return example;
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public int getAllCount() {
        return categoryMapper.selectCount(null);
    }

    @Override
    public Category getById(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getByCategoryName(String categoryName) {
        Example e = new Example(Category.class);
        e.createCriteria().andEqualTo("categoryName",categoryName);
        List<Category> list = categoryMapper.selectByExample(e);
        if(list.size() != 0) {
            return list.get(0).getCategoryId();
        } else {
            return 0;
        }
    }
}
