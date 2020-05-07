package com.wills.blog.service.impl;

import com.wills.blog.bean.*;
import com.wills.blog.dao.PermissionMapper;
import com.wills.blog.dao.PermissionRoleMapper;
import com.wills.blog.dao.RoleMapper;
import com.wills.blog.dao.RoleUserMapper;
import com.wills.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private PermissionRoleMapper prmapper;
    @Autowired
    private PermissionMapper pMapper;


    @Override
    public List<Role> getAllRoles() {
        List<Role> list = roleMapper.selectAll();
        if(list != null){
            for (Role role : list) {
                Example e = new Example(PermissionRole.class);
                Example.Criteria c = e.createCriteria();
                c.andEqualTo("roleId",role.getRoleId());
                List<PermissionRole> roleList = prmapper.selectByExample(e);
                if(roleList.size() != 0) {
                    List<Permission> pList = new ArrayList<>();
                    for (PermissionRole pp : roleList) {
                        Permission permission = pMapper.selectByPrimaryKey(pp.getPermissionId());
                        pList.add(permission);
                    }
                    role.setAllPermission(pList);
                }
            }
        }
        return list;
    }

    @Override
    public int getRolesCount() {
        return roleMapper.selectCount(null);
    }

    @Override
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void delete(Role role) {
        roleMapper.delete(role);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public List<Role> getUserRole(int userId) {
        Example example = new Example(RoleUser.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("userId",userId);
        List<RoleUser> roleUsers =  roleUserMapper.selectByExample(example);
        List<Role> all = new ArrayList<>();
        for (RoleUser roleUser : roleUsers) {
            Role role = roleMapper.selectByPrimaryKey(roleUser.getRoleId());
            all.add(role);
        }
        return all;
    }
}
