package com.wills.blog.service;

import com.wills.blog.bean.Category;

import java.util.List;

public interface CategoryService {

    public void add(Category category);

    public void delete(int categoryId);

    public void update(Category category);

    public List<Category> getAll();

    public int getAllCount();

    public Category getById(int id);

    int getByCategoryName(String categoryName);
}
