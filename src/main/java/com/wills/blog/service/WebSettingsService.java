package com.wills.blog.service;

import com.wills.blog.bean.WebSettings;

import java.util.List;

public interface WebSettingsService {

    public List<WebSettings> getQiNiuInfo();

    public List<WebSettings> getAll();

    public void update(List<WebSettings> webSettings);
}
