package com.wills.blog.service.impl;

import com.wills.blog.bean.Log;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.dao.LogDao;
import com.wills.blog.service.LogService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;


    @Override
    public void insert(Log log) {
        logDao.insert(log);
    }

    @Override
    public List<Log> getAll(WillsPageHelper willsPageHelper) {
        int start = willsPageHelper.getStart()*willsPageHelper.getPerCount();
        int end = start + willsPageHelper.getPerCount();
        return logDao.getAll(start,end);
    }
}
