package com.wills.blog.controller;

import com.wills.blog.bean.Base;
import com.wills.blog.bean.Result;
import com.wills.blog.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "后台导航")
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @GetMapping("/getAllNav")
    @ApiOperation(value = "获取所有的后台导航")
    @RequiresRoles(value = {"系统管理员"})
    public Result getAllNav(){
        List<Base> all = baseService.getAll();
        return Result.buildSuccess(all);
    }
}
