package com.wills.blog.expection;

import com.wills.blog.bean.Result;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //全局异常处理
public class WillsExpection {

    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseBody
    public Result unauthenticatedException(Exception e){
        return new Result(false,477,"对不起您没有登陆！！！请您重新登录",null);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Result unauthorizedException(Exception e){
        return new Result(false,488,"对不起您没有对应的权限！！！请您重新登录",null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        return Result.buildServerError();
    }
}
