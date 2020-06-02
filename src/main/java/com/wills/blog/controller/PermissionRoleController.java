package com.wills.blog.controller;

import com.wills.blog.bean.Permission;
import com.wills.blog.bean.PermissionRole;
import com.wills.blog.bean.Result;
import com.wills.blog.bean.StatusCode;
import com.wills.blog.service.PermissionRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "角色权限")
@RequestMapping("rolePermission")
public class PermissionRoleController {

    @Autowired
    private PermissionRoleService p;

    @ApiOperation(value = "更新角色权限")
    @PostMapping("update/{roleId}")
    @RequiresRoles(value = {"系统管理员"})
    public Result update(@PathVariable("roleId")int roleId, @RequestBody List<Integer> all){
        p.operate(roleId,all);
        return new Result();
    }

    @ApiOperation(value = "获取角色权限")
    @GetMapping("get/{roleId}")
    public Result<Map<String,Object>> add(@PathVariable("roleId")int roleId){
        List<Permission> all = p.getAll(roleId);
        int count = p.getAllCount(roleId);
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("allPermission",all);
        m.put("total",count);
        return new Result(true, StatusCode.STATUS_OK,"获取角色权限成功！",m);
    }

}
