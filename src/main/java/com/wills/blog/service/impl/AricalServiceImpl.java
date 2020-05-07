package com.wills.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.wills.blog.bean.*;
import com.wills.blog.dao.ArticalMapper;
import com.wills.blog.dao.CategoryMapper;
import com.wills.blog.dao.CommentMapper;
import com.wills.blog.dao.TagMapper;
import com.wills.blog.service.ArticalService;
import com.wills.blog.service.CategoryService;
import com.wills.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AricalServiceImpl implements ArticalService {

    @Autowired
    private ArticalMapper articalMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;

    public int getCategoryId(String condition) {
        List<Category> categoryList = categoryService.getAll();
        int cateId = 0;
        for (Category category : categoryList) {
            if(category.getCategoryUrl().equals(condition)){
                cateId = category.getCategoryId();
                break;
            }
        }
        return cateId;
    }


    @Override
    public Map<String, Object> get(WillsPageHelper willsPageHelper, String condition) {
//        PageHelper.startPage(willsPageHelper.getStart(),willsPageHelper.getPerCount());
        Map<String,Object> m = new HashMap<>();
        int cateId = getCategoryId(condition);
        if(cateId != 0) {
            Example e = new Example(Artical.class);
            e.createCriteria().andEqualTo("categoryId",cateId);
            int total = articalMapper.selectCountByExample(e);
            int start = (willsPageHelper.getStart()-1)*willsPageHelper.getPerCount();
            int end = willsPageHelper.getPerCount();
            List<Artical> all = articalMapper.getByCategoryId(cateId,start,end);
            m.put("allArticle",fullData(all));
            m.put("total",total);
            return m;
        } else {
            return null;
        }
    }

    @Override
    public List<Artical> getAllPass(WillsPageHelper willsPageHelper) {
        String orderBy = "artical_id desc";
        PageHelper.startPage(willsPageHelper.getStart(),willsPageHelper.getPerCount(),orderBy);
        List<Artical> all = articalMapper.getAll();
        return fullData(all);
    }

    @Override
    public List<Artical> getAll(WillsPageHelper pageHelper) throws ParseException {
        String orderBy = "artical_id desc";
        PageHelper.startPage(pageHelper.getStart(),pageHelper.getPerCount(),orderBy);
        List<Artical> all = articalMapper.selectAll();
        return fullData(all);
    }

    public List<Artical> fullData(List<Artical> all) {
        for (Artical artical : all) {
            Example e = new Example(Comment.class);
            e.createCriteria().andEqualTo("articleId",artical.getArticalId());
            artical.setComments(commentMapper.selectByExample(e));
            artical.setCommentCount(commentMapper.articleCount(artical.getArticalId()));
            artical.setCategoryName(categoryMapper.selectByPrimaryKey(artical.getCategoryId()).getCategoryName());
            // TODO like暂时不实现，等实现再来补充！
            artical.setLike(0);
        }
        return all;
    }

    @Override
    public int getAllCount() {
        return articalMapper.selectCount(null);
    }

    @Override
    public int getAllPassCount() {
        return articalMapper.getAllPassCount();
    }

    @Override
    public void add(Artical artical) {
        articalMapper.insert(artical);
    }

    @Override
    public void update(Artical artical) {
        articalMapper.updateByPrimaryKey(artical);
    }

    @Override
    public void delete(int articalId) {
        articalMapper.deleteByPrimaryKey(articalId);
    }

    @Override
    public Artical getArticalByPK(int articalId) {
        Artical artical = articalMapper.selectByPrimaryKey(articalId);
        artical.setCategoryName(categoryMapper.selectByPrimaryKey(artical.getCategoryId()).getCategoryName());
        String[] t = artical.getTag().split(",");
        List<Tag> tagNames = new ArrayList<>();
        for (String s : t) {
            tagNames.add(tagMapper.selectByPrimaryKey(Integer.parseInt(s)));
        }
        artical.setTags(tagNames);
        return artical;
    }

    @Override
    public void changeStatus(int articalId) {
        articalMapper.changeStatus(articalId);
    }

    @Override
    public void pass(int id) {
        Artical artical = articalMapper.selectByPrimaryKey(id);
        if(artical != null) {
            artical.setStatus(1);
        }
        articalMapper.updateByPrimaryKeySelective(artical);
    }

    @Override
    public int getAllView() {
        return articalMapper.getAllView();
    }

    @Override
    public Map<String, Object> getCategoryCount() {
        List<Category> all = categoryService.getAll();
        Map<String, Object> m = new HashMap<>();
        List<ArticleCategory> m1 = new ArrayList<>();
        List<String> m2 = new ArrayList<>();
        for (Category category : all) {
            Example example = getExampleByCk(category.getCategoryId());
            int count = articalMapper.selectCountByExample(example);
            m1.add(new ArticleCategory(category.getCategoryName(),count+""));
            m2.add(category.getCategoryName());
        }
        m.put("categoryCount",m1);
        m.put("categoryName",m2);
        return m;
    }

    @Override
    public List<Artical> getByCondition(Artical artical) {
        Example example = new Example(Artical.class);
        Example.Criteria c = example.createCriteria();
        if(artical.getCategoryId() != 0) {
            c.andEqualTo("categoryId",artical.getCategoryId());
        }
        c.andEqualTo("status",artical.getStatus());
        if(artical.getArticalTitle() != "") {
            c.andLike("articalTitle",artical.getArticalTitle());
        }
        return articalMapper.selectByExample(example);
    }

    public Example getExampleByCk(int categoryId) {
        Example example = new Example(Artical.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("categoryId",categoryId);
        return example;
    }

    public Example getExampleByPK(int articalId){
        Example example = new Example(Artical.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("articalId",articalId);
        return example;
    }


}
