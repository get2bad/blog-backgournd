package com.wills.blog.controller;

import com.wills.blog.bean.Note;
import com.wills.blog.bean.Result;
import com.wills.blog.bean.StatusCode;
import com.wills.blog.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("note")
@Api(tags = "留言板")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PutMapping("add")
    @ApiOperation(value = "增加留言")
    public Result add(@RequestBody Note note){
        noteService.add(note);
        return new Result();
    }

    @DeleteMapping("delete/{noteId}")
    @ApiOperation(value = "删除留言")
    public Result delete(@PathVariable("noteId") int noteId){
        noteService.delete(noteId);
        return new Result();
    }

    @PostMapping("update")
    @ApiOperation(value = "更新留言")
    public Result update(@RequestBody Note note){
        noteService.update(note);
        return new Result();
    }

    @GetMapping("getAll/{userId}")
    @ApiOperation(value = "获得所有的留言")
    public Result<Map<String,Object>> getAll(@PathVariable("userId") int userId){
        List<Note> all = new ArrayList<Note>();
        if(userId == 0) {
            all = noteService.getAll();
        }else {
            all = noteService.getAll(userId);
        }
        int total = noteService.getAllCount();
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("allNote",all);
        m.put("total",total);
        return new Result<Map<String,Object>>(true, StatusCode.STATUS_OK,"获取所有信息成功！",m);
    }
}
