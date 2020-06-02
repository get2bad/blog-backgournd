package com.wills.blog.controller;

import com.wills.blog.bean.Construct;
import com.wills.blog.bean.Result;
import com.wills.blog.service.ConstructService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "页面结构")
@RequestMapping("construct")
public class ConstructController {

    @Autowired
    private ConstructService service;

    @PutMapping("add")
    @ApiOperation(value = "添加结构信息")
    @RequiresRoles(value = {"系统管理员"})
    public Result add(@RequestBody Construct construct){
        service.add(construct);
        return Result.buildSuccess();
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除结构信息")
    @RequiresRoles(value = {"系统管理员"})
    public Result delete(@PathVariable("id") int id){
        service.delete(id);
        return Result.buildSuccess();
    }

    @PostMapping("update")
    @ApiOperation(value = "更新结构信息")
    @RequiresRoles(value = {"系统管理员"})
    public Result update(@RequestBody Construct construct){
        service.update(construct);
        return Result.buildSuccess();
    }

    @GetMapping("getAll")
    @ApiOperation(value = "获取结构信息")
    public Result getAll(){
        Map<String,Object> m = new HashMap<>();
        m.put("allConfig",service.getAll());
        m.put("total",service.getAllCount());
        return Result.buildSuccess(m);
    }
}
