package com.wills.blog.service.impl;

import com.wills.blog.dao.UserInfoMapper;
import com.wills.blog.bean.UserInfo;
import com.wills.blog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void regist(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    @Override
    public UserInfo getByUserId(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
