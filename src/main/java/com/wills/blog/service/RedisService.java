package com.wills.blog.service;

public interface RedisService {

    public void add(String key,String value);

    public void delete(String key);

    public void update(String key,String value);

    public void getAll();

    public String getOneByKey(String key);

    public void login(String token,String value);

    public boolean ifKeyExists(String key);

}
