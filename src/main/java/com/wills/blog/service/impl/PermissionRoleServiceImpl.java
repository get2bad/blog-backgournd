package com.wills.blog.service.impl;

import com.wills.blog.dao.PermissionMapper;
import com.wills.blog.dao.PermissionRoleMapper;
import com.wills.blog.bean.Permission;
import com.wills.blog.bean.PermissionRole;
import com.wills.blog.service.PermissionRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class PermissionRoleServiceImpl implements PermissionRoleService {

    @Autowired
    private PermissionRoleMapper permissionRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public void operate(int roleId, List<Integer> all) {
        // 先删除后增加
        permissionRoleMapper.deleteByRoleId(roleId);
        for (Integer pId : all) {
            permissionRoleMapper.insert(new PermissionRole(pId,roleId));
        }
    }

    public Example opearte(int roleId){
        Example example = new Example(PermissionRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        return example;
    }

    @Override
    public List<Permission> getAll(int roleId) {
        List<PermissionRole> roles = permissionRoleMapper.selectByExample(opearte(roleId));
        List<Permission> all = new ArrayList<Permission>();
        for (PermissionRole role : roles) {
            Permission permission = permissionMapper.selectByPrimaryKey(role.getPermissionId());
            if(permission.getLevel() ==0 || permission.getLevel() ==1){
                // 如果权限的等级为0或者为1则查询其他权限的parent的ID为这个的，然后将其添加至这个permission的Child中
                Example example = new Example(Permission.class);
                Example.Criteria c = example.createCriteria();
                c.andEqualTo("parentId",permission.getPermissionId());
                List<Permission> list = permissionMapper.selectByExample(example);
                permission.setChild(list);
            }
            all.add(permission);
        }
        return all;
    }

    @Override
    public int getAllCount(int roleId) {
        int total = permissionRoleMapper.selectCount(new PermissionRole(roleId));
        return total;
    }
}
