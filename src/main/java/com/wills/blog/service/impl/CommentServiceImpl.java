package com.wills.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.dao.CommentMapper;
import com.wills.blog.bean.Comment;
import com.wills.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getAll(WillsPageHelper willsPageHelper) {
        if(willsPageHelper !=null){
            PageHelper.startPage(willsPageHelper.getStart(),willsPageHelper.getPerCount());
        }
        return commentMapper.selectAll();
    }

    @Override
    public int getAllCount() {
        return commentMapper.selectCount(null);
    }

    @Override
    public void add(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public void update(Comment comment) {
        commentMapper.updateByPrimaryKey(comment);
    }

    @Override
    public void deleteByPK(int commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public void changeStatus(int id) {
        commentMapper.changeStatus(id);
    }

    @Override
    public List<Comment> get(int id, WillsPageHelper willsPageHelper) {
        if(willsPageHelper !=null){
            PageHelper.startPage(willsPageHelper.getStart(),willsPageHelper.getPerCount());
        }
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("articleId",id);
        return commentMapper.selectByExample(example);
    }

    @Override
    public int getArticleCommentCount(int id) {
        return commentMapper.articleCount(id);
    }
}
