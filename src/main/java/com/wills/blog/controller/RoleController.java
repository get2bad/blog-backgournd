package com.wills.blog.controller;

import com.wills.blog.bean.Result;
import com.wills.blog.bean.Role;
import com.wills.blog.bean.RoleTotal;
import com.wills.blog.bean.StatusCode;
import com.wills.blog.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("role")
@Api(tags = "角色信息")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("getAll")
    @ApiOperation(value = "获取所有角色列表")
    @RequiresRoles(value = {"系统管理员"})
    public Result<Map<String,Object>> getAll(){
        Map<String,Object> m = new HashMap<>();
        List<Role> allRoles = roleService.getAllRoles();
        m.put("allRoles",allRoles);
        int count = roleService.getRolesCount();
        m.put("total",count);
        return new Result<Map<String,Object>>(true, StatusCode.STATUS_OK,"获取用户列表成功！,",m);
    }

    @PutMapping("add")
    @ApiOperation(value = "添加角色操作")
    @RequiresRoles(value = {"系统管理员"})
    public Result add(@RequestBody Role role){
        roleService.add(role);
        return new Result();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色操作")
    @RequiresRoles(value = {"系统管理员"})
    public Result delete(@PathVariable("id")int id){
        roleService.delete(new Role(id));
        return new Result();
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改角色相关信息")
    @RequiresRoles(value = {"系统管理员"})
    public Result update(@RequestBody Role role){
        // TODO 暂时写最基本的role修改示例
        roleService.update(role);
        return new Result();
    }
}
