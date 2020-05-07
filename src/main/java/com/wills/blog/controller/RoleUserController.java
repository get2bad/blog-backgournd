package com.wills.blog.controller;

import com.wills.blog.bean.Result;
import com.wills.blog.bean.Role;
import com.wills.blog.bean.StatusCode;
import com.wills.blog.service.RoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/role")
@Api(tags = "用户权限")
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除某一用户的所有角色")
    // 更新也使用同一个方法
    public Result delete(@PathVariable("id") int id, @RequestBody List<Role> all){
        roleUserService.update(id,all);
        return new Result();
    }

    @PostMapping("update/{id}")
    @ApiOperation(value = "更新某一用户的所有角色")
    // 更新也使用同一个方法
    public Result update(@PathVariable("id") int id, @RequestBody List<Role> all){
        roleUserService.update(id,all);
        return new Result();
    }

    @PutMapping("add/{id}")
    @ApiOperation(value = "增加某一用户的所有角色")
    // 更新也使用同一个方法
    public Result add(@PathVariable("id") int id, @RequestBody List<Role> all){
        roleUserService.update(id,all);
        return new Result();
    }

    @GetMapping("/getAll/{id}")
    @ApiOperation(value = "获取某一用户的角色列表")
    public Result<List<Role>> getAll(@PathVariable("id") int userId){
        List<Role> list = roleUserService.getUserRole(userId);
        return new Result<List<Role>>(true, StatusCode.STATUS_OK,"获取用户角色列表成功！",list);
    }
}
