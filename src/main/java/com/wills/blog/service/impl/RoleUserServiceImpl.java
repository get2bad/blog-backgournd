package com.wills.blog.service.impl;

import com.wills.blog.dao.RoleMapper;
import com.wills.blog.dao.RoleUserMapper;
import com.wills.blog.bean.Role;
import com.wills.blog.bean.RoleUser;
import com.wills.blog.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getUserRole(int userId) {
        List<RoleUser> all = roleUserMapper.selectByExample(getUserIdRole(userId));
        List<Role> roleList = new ArrayList<Role>();
        if(all.size() != 0) {
            for(int i=0;i<all.size();i++){
                Role role = roleMapper.selectByPrimaryKey(all.get(i).getRoleId());
                roleList.add(role);
            }
        }
        return roleList;
    }

    public Example getUserIdRole(int userId){
        Example e = new Example(RoleUser.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("userId",userId);
        return e;
    }

    @Override
    public void update(int userId, List<Role> roleList) {
        //思路 ： 先删除所有关于这个userId的权限，然后重新插入
        roleUserMapper.deleteByExample(deleteExample(userId));
        if(roleList.size() !=0){
            for(int i =0;i<roleList.size();i++){
                roleUserMapper.insert(new RoleUser(userId,roleList.get(i).getRoleId()));
            }
        }
    }

    public Example deleteExample(int userId){
        Example example = new Example(RoleUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        return example;
    }
}
