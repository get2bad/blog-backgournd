package com.wills.blog.controller;

import com.wills.blog.bean.Result;
import com.wills.blog.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 应该修改为多线程获取！要不单线程获取会出现BUG！！切记！使用线程池去解决
 */
@RestController
@RequestMapping("/system")
@Api(tags = "获取系统信息")
public class SystemInfoController {

    @Autowired
    private SystemService service;

    @GetMapping("/cpu")
    @ApiOperation(value = "获取cpu的占用率")
    public Result getCpu() throws InterruptedException {
        return Result.buildSuccess(service.getCpu());
    }

    @GetMapping("/disk")
    @ApiOperation(value = "获取disk的占用率")
    public Result getDisk(){
        return Result.buildSuccess(service.getDisk());
    }

    @GetMapping("/memory")
    @ApiOperation(value = "获取内存的占用率")
    public Result getMem(){
        return Result.buildSuccess(service.getMem());
    }

    @GetMapping("/hardware")
    @ApiOperation(value = "获取系统硬件")
    public Result getHardware(){
        return Result.buildSuccess(service.getHardware());
    }

    @GetMapping("/article")
    @ApiOperation(value = "获取文章分类信息")
    public Result article(){
        return Result.buildSuccess(service.article());
    }
}
