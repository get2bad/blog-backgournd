package com.wills.blog.service;

import com.wills.blog.bean.File;

import java.util.List;

public interface FileService {

    public void add(File file);

    public void delete(int fileId);

    public void delete(String fileKey);

    public void update(File file);

    public List<File> getAll();

    public int getAllCount();
}
