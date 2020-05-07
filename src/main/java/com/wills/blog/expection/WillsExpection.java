package com.wills.blog.expection;

import com.wills.blog.bean.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// @ControllerAdvice //全局异常处理
public class WillsExpection {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        return Result.buildServerError();
    }
}
