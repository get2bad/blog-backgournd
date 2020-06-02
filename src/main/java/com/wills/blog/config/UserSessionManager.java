package com.wills.blog.config;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 前后端分离必须要重写本SessionManager类，来达到获取请求头的作用
 */
public class UserSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader("token");
        if(token !=null && "".equals(token)){
            //将SessionId的Source发送到前端
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            //将SessionId发送到前端
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,token);
            //将SessionId验证成功发送到前端
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return token;
        }else{
            return super.getSessionId(request, response);
        }
    }
}
