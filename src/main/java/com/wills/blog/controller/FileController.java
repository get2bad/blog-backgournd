package com.wills.blog.controller;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.util.Auth;
import com.wills.blog.bean.File;
import com.wills.blog.bean.Result;
import com.wills.blog.bean.WebSettings;
import com.wills.blog.service.FileService;
import com.wills.blog.service.WebSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("file")
@Api(tags = "文件上传")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private WebSettingsService webService;

    @PutMapping("add")
    @ApiOperation(value = "文件上传")
    public Result add(@RequestBody File file){
        System.out.println(file);
        fileService.add(file);
        return Result.buildSuccess();
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "文件删除")
    public Result delete(@PathVariable("id") int fileId){
        fileService.delete(fileId);
        return Result.buildSuccess();
    }

    @PostMapping("deleteKey")
    @ApiOperation(value = "文件删除")
    public Result delete(@RequestBody File file) throws QiniuException {
        //数据库删除文件信息
        String path = file.getFilePath();
        fileService.delete(path);
        //七牛云删除文件
        Configuration c = new Configuration(Region.region0());
        List<WebSettings> info = webService.getQiNiuInfo();
        Auth auth = Auth.create(info.get(0).getWebValue(), info.get(1).getWebValue());
        BucketManager manager = new BucketManager(auth,c);
        manager.delete(info.get(2).getWebValue(),file.getFileKey());
        return Result.buildSuccess();
    }

    @PostMapping("update")
    @ApiOperation(value = "文件修改")
    public Result update(@RequestBody File file){
        fileService.update(file);
        return Result.buildSuccess();
    }

    @GetMapping("getAll")
    @ApiOperation(value = "获取所有文件信息")
    public Result getAll(){
        Map<String,Object> m = new HashMap<>();
        m.put("allFile",fileService.getAll());
        m.put("total",fileService.getAllCount());
        return Result.buildSuccess(m);
    }
}
