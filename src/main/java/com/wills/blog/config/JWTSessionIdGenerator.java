package com.wills.blog.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

public class JWTSessionIdGenerator implements SessionIdGenerator {


    @Override
    public Serializable generateId(Session session) {
        return UUID.randomUUID().toString().replace("-","");
    }
}
