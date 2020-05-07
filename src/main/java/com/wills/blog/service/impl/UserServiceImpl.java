package com.wills.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wills.blog.dao.UserInfoMapper;
import com.wills.blog.dao.UserMapper;
import com.wills.blog.bean.UserInfo;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.bean.User;
import com.wills.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int getTotalCount() {
        return userMapper.selectCountByExample(null);
    }

    @Override
    public List<User> getAllUser(WillsPageHelper pageHelper) {
        PageHelper.startPage(pageHelper.getStart(),pageHelper.getPerCount());
        return userMapper.selectAll();
    }

    @Override
    public void regist(User user) {
        userMapper.insert(user);
    }

    // 对用户名进行条件查询
    @Override
    public User findOne(User user) {
        Example example = createExample(user);
        List<User> list = userMapper.selectByExample(example);
        if(list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void updateUserData(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public void banUser(int id) {
        userMapper.banUser(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void resetPwd(int id, String newPwd) {
        userMapper.resetPwd(id,newPwd);
    }

    @Override
    public User getById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public Example createExample(User user){
        Example e = new Example(User.class);
        Example.Criteria c = e.createCriteria();
        if(user != null){
            if(!StringUtils.isEmpty(user.getUserName())){
                c.andLike("userName","%" + user.getUserName() + "%");
            }
        }
        return e;
    }
}
