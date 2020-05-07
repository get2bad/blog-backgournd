package com.wills.blog.controller;

import com.wills.blog.bean.Comment;
import com.wills.blog.bean.Result;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "评论")
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PutMapping("add")
    @ApiOperation(value = "增加评论")
    public Result add(@RequestBody Comment comment){
        comment.setPostTime(new Date());
        commentService.add(comment);
        return Result.buildSuccess();
    }

    @DeleteMapping("delete/{commentId}")
    @ApiOperation(value = "删除评论")
    public Result delete(@PathVariable("commentId") int commentId){
        commentService.deleteByPK(commentId);
        return Result.buildSuccess();
    }

    @PostMapping("update")
    @ApiOperation(value = "修改评论")
    public Result update(@RequestBody Comment comment){
        commentService.update(comment);
        return Result.buildSuccess();
    }

    @PostMapping("getAll")
    @ApiOperation(value = "获取所有的评论")
    public Result getAll(@RequestBody(required = false) WillsPageHelper willsPageHelper){
        Map<String,Object> m= new HashMap<>();
        m.put("allComment",commentService.getAll(willsPageHelper));
        m.put("total",commentService.getAllCount());
        return Result.buildSuccess(m);
    }

    @PostMapping("get/{id}")
    @ApiOperation(value = "获取特定文章ID的评论")
    public Result getAll(@PathVariable("id") int id, @RequestBody(required = false) WillsPageHelper willsPageHelper){
        Map<String,Object> m = new HashMap<>();
        List<Comment> list = commentService.get(id, willsPageHelper);
        int total = commentService.getArticleCommentCount(id);
        m.put("allComment",list);
        m.put("total",total);
        return Result.buildSuccess(m);
    }

    @GetMapping("changeStatus/{id}")
    @ApiOperation(value = "更改评论的显示状态")
    public Result changeStatus(@PathVariable("id") int id) {
        commentService.changeStatus(id);
        return Result.buildSuccess();
    }

}
