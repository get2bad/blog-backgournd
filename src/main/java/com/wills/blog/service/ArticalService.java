package com.wills.blog.service;

import com.wills.blog.bean.Artical;
import com.wills.blog.bean.WillsPageHelper;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ArticalService {

    public List<Artical> getAll(WillsPageHelper pageHelper) throws ParseException;

    public int getAllCount();

    public int getAllPassCount();

    public void add(Artical artical);

    public void update(Artical artical);

    public void delete(int articalId);

    public Artical getArticalByPK(int articalId);

    public void changeStatus(int articalId);

    public void pass(int id);

    public int getAllView();

    public Map<String, Object> getCategoryCount();

    public List<Artical> getByCondition(Artical artical);

    Map<String, Object> get(WillsPageHelper willsPageHelper,String condition);

    List<Artical> getAllPass(WillsPageHelper willsPageHelper);
}
