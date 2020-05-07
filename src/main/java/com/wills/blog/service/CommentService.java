package com.wills.blog.service;

import com.wills.blog.bean.Comment;
import com.wills.blog.bean.WillsPageHelper;

import java.util.List;
import java.util.Map;

public interface CommentService {

    public List<Comment> getAll(WillsPageHelper willsPageHelper);

    public int getAllCount();

    public void add(Comment comment);

    public void update(Comment comment);

    public void deleteByPK(int commentId);

    public void changeStatus(int id);

    public List<Comment> get(int id,WillsPageHelper willsPageHelper);

    public int getArticleCommentCount(int id);
}
