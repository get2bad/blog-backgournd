package com.wills.blog.service.impl;

import com.wills.blog.dao.FileMapper;
import com.wills.blog.bean.File;
import com.wills.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public void add(File file) {
        fileMapper.insert(file);
    }

    @Override
    public void delete(int fileId) {
        fileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public void delete(String fileKey) {
        Example example = new Example(File.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("filePath",fileKey);
        fileMapper.deleteByExample(example);
    }

    @Override
    public void update(File file) {
        fileMapper.updateByPrimaryKey(file);
    }

    @Override
    public List<File> getAll() {
        return fileMapper.selectAll();
    }

    @Override
    public int getAllCount() {
        return fileMapper.selectCount(null);
    }
}
