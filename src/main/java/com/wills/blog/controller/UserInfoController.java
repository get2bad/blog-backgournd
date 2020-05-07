package com.wills.blog.controller;

import com.wills.blog.bean.Result;
import com.wills.blog.bean.UserInfo;
import com.wills.blog.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户详细信息")
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    @ApiOperation(value = "获取指定用户信息")
    @GetMapping("/get/{id}")
    public Result getUserInfo(@PathVariable("id") int id){
        UserInfo byUserId = service.getByUserId(id);
        return Result.buildSuccess(byUserId);
    }
}
