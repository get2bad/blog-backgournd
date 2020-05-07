package com.wills.blog.service;

import com.wills.blog.bean.Menu;
import com.wills.blog.bean.WillsPageHelper;

import java.util.List;

public interface MenuService {

    public void add(Menu menu);

    public void update(Menu menu);

    public void delete(int id);

    public List<Menu> getAll(WillsPageHelper pageHelper);

    public int getAllCount();
}
