package com.wills.blog.controller;

import com.wills.blog.bean.Config;
import com.wills.blog.bean.Result;
import com.wills.blog.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "配置")
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("getAll")
    @ApiOperation(value = "获取所有的配置信息")
    public Result getAll(){
        Map<String,Object> m = new HashMap<>();
        m.put("allConfig",configService.getAll());
        m.put("total",configService.getAllCount());
        return Result.buildSuccess(m);
    }

    @PutMapping("add")
    @ApiOperation(value = "添加配置信息")
    public Result add(@RequestBody Config config){
        configService.add(config);
        return Result.buildSuccess();
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除配置信息")
    public Result delete(@PathVariable("id") int id){
        configService.delete(id);
        return Result.buildSuccess();
    }

    @PostMapping("update")
    @ApiOperation(value = "修改配置信息")
    public Result update(@RequestBody Config config){
        configService.update(config);
        return Result.buildSuccess();
    }
}
