package com.wills.blog.controller;

import com.wills.blog.bean.Category;
import com.wills.blog.bean.Result;
import com.wills.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
@Api(tags = "栏目")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 增加栏目
     * @param category
     * @return
     */
    @PutMapping("add")
    @ApiOperation(value = "增加栏目")
    @RequiresRoles(value = {"系统管理员"})
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.buildSuccess();
    }

    /**
     * 删除栏目
     * @param categoryId
     * @return
     */
    @DeleteMapping("delete")
    @ApiOperation(value = "删除栏目")
    @RequiresRoles(value = {"系统管理员"})
    public Result delete(int categoryId){
        categoryService.delete(categoryId);
        return Result.buildSuccess();
    }

    /**
     * 修改栏目信息
     * @param category
     * @return
     */
    @PostMapping("update")
    @ApiOperation(value = "修改栏目信息")
    @RequiresRoles(value = {"系统管理员"})
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return Result.buildSuccess();
    }

    /**
     * 获取所有栏目
     * @return
     */
    @GetMapping("getAll")
    @ApiOperation(value = "获取所有的栏目")
    public Result getAll(){
        List<Category> all = categoryService.getAll();
        int count = categoryService.getAllCount();
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("AllCategory",all);
        m.put("total",count);
        return Result.buildSuccess(m);
    }

    /**
     * 获取指定Name的Category
     * @param categoryName
     * @return
     */
    @GetMapping("/getByCateName/{categoryName}")
    @ApiOperation(value = "获取指定Name的Category")
    public Result getById(@PathVariable("categoryName") String categoryName){
        int c = categoryService.getByCategoryName(categoryName);
        return Result.buildSuccess(c);
    }

    /**
     * 获取指定ID的Category
     * @param id
     * @return
     */
    @GetMapping("/getAll/{id}")
    @ApiOperation(value = "获取指定ID的Category")
    public Result getById(@PathVariable("id") int id){
        Category c = categoryService.getById(id);
        return Result.buildSuccess(c);
    }
}
