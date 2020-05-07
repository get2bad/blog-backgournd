package com.wills.blog.controller;

import com.wills.blog.bean.Menu;
import com.wills.blog.bean.Result;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("menu")
@Api(tags = "菜单权限")
public class MenuController {

    @Autowired
    private MenuService service;

    @PutMapping("add")
    @ApiOperation(value = "添加结构信息")
    public Result add(@RequestBody Menu menu){
        service.add(menu);
        return Result.buildSuccess();
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除结构信息")
    public Result delete(@PathVariable("id") int id){
        service.delete(id);
        return Result.buildSuccess();
    }

    @PostMapping("update")
    @ApiOperation(value = "更新结构信息")
    public Result update(@RequestBody Menu menu){
        service.update(menu);
        return Result.buildSuccess();
    }

    @PostMapping("getAll")
    @ApiOperation(value = "获取结构信息")
    public Result getAll(@RequestBody WillsPageHelper pageHelper){
        Map<String,Object> m = new HashMap<>();
        m.put("allConfig",service.getAll(pageHelper));
        m.put("total",service.getAllCount());
        return Result.buildSuccess(m);
    }

}
