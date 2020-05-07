package com.wills.blog.controller;

import com.wills.blog.bean.*;
import com.wills.blog.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("permission")
@Api(tags = "权限信息")
public class PermissionController {

    @Autowired
    private PermissionService p;

    @PostMapping("getAll")
    @ApiOperation(value = "获取所有的权限信息")
    public Result<Map<String,Object>> getAll(@RequestBody WillsPageHelper pageHelper){
        Map<String,Object> m = new HashMap<String,Object>();
        List<Permission> all = p.getAll(pageHelper);
        m.put("allPermission",all);
        int count = p.getAllCount();
        m.put("total",count);
        return new Result<Map<String,Object>>(true, StatusCode.STATUS_OK,"获取所有权限成功",m);
    }

    @PutMapping("add")
    @ApiOperation(value = "添加权限信息")
    public Result add(@RequestBody Permission permission){
        p.add(permission);
        return new Result();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除特定ID的权限信息")
    public Result delete(@PathVariable("id") int id){
        p.delete(new Permission(id));
        return new Result();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新特定ID的权限信息")
    public Result update(@RequestBody Permission permission){
        p.update(permission);
        return new Result();
    }
}
