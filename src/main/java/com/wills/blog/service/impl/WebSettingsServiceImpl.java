package com.wills.blog.service.impl;

import com.wills.blog.bean.WebSettings;
import com.wills.blog.dao.WebSettingsMapper;
import com.wills.blog.service.WebSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WebSettingsServiceImpl implements WebSettingsService {

    @Autowired
    private WebSettingsMapper mapper;

    @Override
    public List<WebSettings> getQiNiuInfo() {
        List<WebSettings> res = new ArrayList<>();
        // id为1,2,3是七牛云的配置信息
        res.add(mapper.selectByPrimaryKey(1));
        res.add(mapper.selectByPrimaryKey(2));
        res.add(mapper.selectByPrimaryKey(3));
        res.add(mapper.selectByPrimaryKey(4));
        res.add(mapper.selectByPrimaryKey(5));
        return res;
    }

    @Override
    public List<WebSettings> getAll() {
        return mapper.selectAll();
    }

    @Override
    public void update(List<WebSettings> webSettings) {
        for (WebSettings webSetting : webSettings) {
            mapper.updateByPrimaryKey(webSetting);
        }
    }
}
