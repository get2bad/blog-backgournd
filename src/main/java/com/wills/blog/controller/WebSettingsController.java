package com.wills.blog.controller;

import com.wills.blog.bean.Result;
import com.wills.blog.bean.WebSettings;
import com.wills.blog.service.WebSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/websettings")
@Api(tags = "获取网站配置信息")
public class WebSettingsController {

    @Autowired
    private WebSettingsService service;

    @GetMapping("/getqiniu")
    @ApiOperation(value = "获取七牛云的配置信息")
    public Result getQiNiu(){
        return Result.buildSuccess(service.getQiNiuInfo());
    }

    @GetMapping("/getToken")
    @ApiOperation(value = "获取七牛云上传的token信息")
    public Result getToken(){
        List<WebSettings> info = service.getQiNiuInfo();
        Auth auth = Auth.create(info.get(0).getWebValue(), info.get(1).getWebValue());
        String token = auth.uploadToken(info.get(2).getWebValue());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("uploadUrl",info.get(3).getWebValue());
        map.put("viewUrl",info.get(4).getWebValue());
        return Result.buildSuccess(map);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取所有网站配置信息")
    public Result getAll(){
        return Result.buildSuccess(service.getAll());
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改网站的配置信息")
    public Result update(@RequestBody List<WebSettings> webSettings){
        service.update(webSettings);
        return Result.buildSuccess();
    }
}
