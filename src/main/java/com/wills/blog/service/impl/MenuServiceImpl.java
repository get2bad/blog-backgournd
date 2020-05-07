package com.wills.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wills.blog.bean.Menu;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.dao.MenuMapper;
import com.wills.blog.dao.MenuPermissionMapper;
import com.wills.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuPermissionMapper mapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void add(Menu menu) {
        mapper.insert(menu);
    }

    @Override
    public void update(Menu menu) {
        mapper.updateByPrimaryKey(menu);
    }

    @Override
    public void delete(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Menu> getAll(WillsPageHelper pageHelper) {
        PageHelper.startPage(pageHelper.getStart(),pageHelper.getPerCount());
        return menuMapper.getAllMenu();
    }

    @Override
    public int getAllCount() {
//        return mapper.selectCount(null);
        return menuMapper.selectCount(null);
    }
}
