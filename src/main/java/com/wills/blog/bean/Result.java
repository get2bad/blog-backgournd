package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "返回的状态信息", value = "状态信息类")
@Data
public class Result<T> implements Serializable {

    @ApiModelProperty(value="执行是否成功，true 成功，false 失败",required = true)
    private boolean flag;
    @ApiModelProperty(value="状态码 200 成功 404 未找到服务 500 服务器遇到问题 600 用户请求错误",required = true)
    private int statusCode;
    @ApiModelProperty(value="返回的提示信息",required = true)
    private String message;
    @ApiModelProperty(value="执行是否成功，true 成功，false 失败",required = true)
    private T data;

    // 请求成功返回
    public Result(boolean flag, int statusCode, String message, T data) {
        this.flag = flag;
        this.statusCode = statusCode;
        this.message = message;
        this.data = (T)data;
    }

    // 请求失败返回
    public Result(boolean flag, int statusCode, String message) {
        this.flag = flag;
        this.statusCode = statusCode;
        this.message = message;
    }

    // 请求失败返回
    public Result() {
        this.flag = true;
        this.statusCode = StatusCode.STATUS_OK;
        this.message = "操作成功！";
    }

    public boolean isFlag() {
        return flag;
    }

    public static Result buildSuccess(){
        return new Result(true,StatusCode.STATUS_OK,"获取成功！");
    }


    public static Result buildSuccess(Object data){
        return new Result(true,StatusCode.STATUS_OK,"获取成功！",data);
    }

    public static Result buildServerError(){
        return new Result(false,StatusCode.STATUS_SEVER_ERROR,"操作失败！");
    }

    public static Result buildServerRequireError(){
        return new Result(false,StatusCode.STATUS_REQUIRE_FALL,"请求失败！");
    }

    public static Result buildServerPermissionError(){
        return new Result(false,StatusCode.STATUS_PERRMISSION_LIMITED,"权限验证失败！");
    }

    public static Result buildServerSourceError(){
        return new Result(false,StatusCode.STATUS_SOURCE_UNKNOWN,"资源加载失败！");
    }

    public static Result buildServerRepeatError(){
        return new Result(false,StatusCode.STATUS_REPEAT_OPERATION,"重复操作！！");
    }
}
