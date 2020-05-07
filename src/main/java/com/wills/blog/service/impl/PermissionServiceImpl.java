package com.wills.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wills.blog.bean.PermissonTotal;
import com.wills.blog.dao.PermissionMapper;
import com.wills.blog.dao.PermissionRoleMapper;
import com.wills.blog.bean.Permission;
import com.wills.blog.bean.PermissionRole;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private PermissionRoleMapper permissionRoleMapper;

    @Override
    public void add(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public void delete(Permission permission) {
        permissionMapper.deleteByExample(deleteChildren(permission));
    }

    public Example deleteChildren(Permission permission){
        Example e = new Example(Permission.class);
        Example.Criteria criteria = e.createCriteria();
        if(permission.getLevel() !=0){
            criteria.orEqualTo("permission_id",permission.getParentId());
            criteria.orEqualTo("parent_id",permission.getParentId());
        }
        return e;
    }

    @Override
    public List<Permission> getAll(WillsPageHelper pageHelper) {
//        PageHelper.startPage(pageHelper.getStart(),pageHelper.getPerCount());
        List<PermissonTotal> goal = new ArrayList<>();
        List<Permission> fatherList = permissionMapper.selectByExample(getPermisson("level", 0));
        List<Permission> level2 = permissionMapper.selectByExample(getPermisson("level", 1));
        List<Permission> level3 = permissionMapper.selectByExample(getPermisson("level", 2));
        for (Permission p1 : fatherList) {
            List<Permission> child1 = new ArrayList<>();
            for (Permission p2 : level2) {
                List<Permission> child2 = new ArrayList<>();
                if(p2.getParentId() == p1.getPermissionId()){
                    child1.add(p2);
                }
                for (Permission p3 : level3) {
                    if(p3.getParentId() == p2.getPermissionId()){
                        child2.add(p3);
                    }
                }
                p2.setChild(child2);
            }
            p1.setChild(child1);
        }
        return fatherList;
    }

    public Example getPermisson(String conditon,int val){
        Example example = new Example(Permission.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo(conditon,val);
        return example;
    }

    @Override
    public int getAllCount() {
        return permissionMapper.selectCountByExample(getPermisson("level", 0));
    }

    @Override
    public List<Permission> getRolePermission(int roleId) {
        Example example = new Example(PermissionRole.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("roleId",roleId);
        List<PermissionRole> permissionRoles=permissionRoleMapper.selectByExample(example);
        List<Permission> all = new ArrayList<>();
        for (PermissionRole permissionRole : permissionRoles) {
            all.add(permissionMapper.selectByPrimaryKey(permissionRole.getPermissionId()));
        }
        return all;
    }
}
