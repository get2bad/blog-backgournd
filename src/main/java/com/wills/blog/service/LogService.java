package com.wills.blog.service;

import com.wills.blog.bean.Log;
import com.wills.blog.bean.WillsPageHelper;

import java.util.List;

public interface LogService {

    // 增加日志
    public void insert(Log log);
    // 查看所有日志
    public List<Log> getAll(WillsPageHelper willsPageHelper);
}
