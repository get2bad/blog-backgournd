package com.wills.blog.service.impl;

import com.wills.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@PropertySource("classpath:Config.properties")
public class RedisServiceImpl implements RedisService {

    @Value("${wills.redis.user.login}")
    private String USER_LOGIN_KEY;

    @Value("${wills.redis.user.role}")
    private String USER_ROLE_KEY;

    @Value("${wills.redis.role.permission}")
    private String ROLE_PERMISSION_KEY;

    @Resource(name = "RedisStringTemplate")
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void add(String key, String value) {
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void update(String key, String value) {

    }

    @Override
    public void getAll() {

    }

    @Override
    public String getOneByKey(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void login(String token,String value) {
        redisTemplate.opsForValue().set(USER_LOGIN_KEY + ":" +  token,value);
        redisTemplate.expire(USER_LOGIN_KEY + ":" +  token,1, TimeUnit.HOURS);
    }

    @Override
    public boolean ifKeyExists(String key) {
        Boolean ifLogin = redisTemplate.hasKey(key);
        return ifLogin;
    }
}
