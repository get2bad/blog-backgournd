package com.wills.blog.service;

import com.wills.blog.bean.BaseInfo;

import java.util.List;
import java.util.Map;

public interface SystemService {

    public String getCpu() throws InterruptedException;

    public String getDisk();

    public String getMem();

    public Map<String, Object> getHardware();

    public Map<String, Object> article();
}
