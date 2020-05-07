package com.wills.blog.service;

import com.wills.blog.bean.UserInfo;

public interface UserInfoService {

    public void regist(UserInfo userInfo);

    public UserInfo getByUserId(int id);
}
