package com.wills.blog.config;

import com.alibaba.fastjson.JSON;
import com.wills.blog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Aspect
@Component
@Slf4j
public class WillsAspect {

    @Resource
    private LogService logService;

//    @Around("execution(public * com.wills.blog.controller..*.*(..))")
//    public void afterController(JoinPoint joinPoint){
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//
//        //获取需要打印的参数信息
//        String requestURI = request.getRequestURI();
//        String method = request.getMethod();
//        String remoteAddr = request.getRemoteAddr();
//        //这里使用的是阿里的fastjson
//        String jsonString = JSON.toJSONString(joinPoint.getArgs());
////        String reg = "/system/[^\\s]*";
////        if(Pattern.matches(reg,requestURI)){
////            System.out.println("匹配到一个！");
////        }
//        //打印信息
//        log.info("------------------------请求信息----------------------------------");
//        log.info("请求时间 ：{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        log.info("remoteAddr: {} ",remoteAddr);
//        log.info("requestURI : {}",requestURI);
//        log.info("Controller : {}",  joinPoint.getTarget().getClass());
//        log.info("method type: {}" ,method);
//        log.info("req paras: {}",jsonString);
//        log.info("------------------------请求信息-----------------------------------");
//    }
}
