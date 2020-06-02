package com.wills.blog.controller;

import com.wills.blog.bean.Result;
import com.wills.blog.bean.Tag;
import com.wills.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("tag")
@Api(tags = "标签的控制层")
public class TagController {

    @Autowired
    private TagService service;

    @GetMapping("getAll")
    @ApiOperation(value = "获取所有的标签")
    public Result getAll() {
        Map<String,Object> map = new HashMap<>();
        map.put("allTag",service.getAll());
        map.put("total",service.getAllCount());
        return Result.buildSuccess(map);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除指定ID的标签")
    @RequiresRoles(value = {"系统管理员"})
    public Result delete(@PathVariable("id") int id) {
        service.deleteById(id);
        return Result.buildSuccess();
    }

    @PutMapping("/add")
    @ApiOperation(value = "增加标签")
    @RequiresRoles(value = {"系统管理员"})
    public Result add(@RequestBody Tag tag) {
        tag.setTagType("info");
        Tag tag1 = service.add(tag);
        return Result.buildSuccess(tag1);
    }
}
