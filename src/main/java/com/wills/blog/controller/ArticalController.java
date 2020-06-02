package com.wills.blog.controller;

import com.alibaba.fastjson.JSON;
import com.wills.blog.bean.Artical;
import com.wills.blog.bean.Result;
import com.wills.blog.bean.WillsPageHelper;
import com.wills.blog.service.ArticalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("article")
@Api(tags = "文章")
@Log4j
public class ArticalController {

    @Autowired
    private ArticalService articalService;
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 从Elasticsearch中搜索文章
     * @param condition
     * @return
     * @throws IOException
     */
    @GetMapping("getByES/{condition}")
    @ApiOperation(value = "文章搜索功能")
    public Result getByCondition(@PathVariable("condition") String condition) throws IOException {
        SearchRequest req = new SearchRequest("wills_blog");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("articalContent", condition);
        HighlightBuilder high = new HighlightBuilder();
        high.field("articalContent");
        high.requireFieldMatch(false);
        high.preTags("<font color=red>");
        high.postTags("</font>");
        builder.highlighter(high);
        builder.query(matchQuery);
        req.source(builder);
        SearchResponse search = client.search(req, RequestOptions.DEFAULT);
        ArrayList<Map<String, Object>> list = new ArrayList();
        for (SearchHit doc : search.getHits().getHits()) {
//            Map<String, Object> map = doc.getSourceAsMap();
//            list.add(map);
            Map<String, HighlightField> fields = doc.getHighlightFields();
            HighlightField content = fields.get("articalContent");
            Map<String, Object> map = doc.getSourceAsMap();
            // 解析高亮的字段
            if( content != null) {
                Text[] texts = content.fragments();
                String newContent = "";
                for (Text text : texts) {
                    // 替换原来的字段
                    newContent += text;
                }
                map.put("articalContent",newContent);
            }
            list.add(map);
        }
        return Result.buildSuccess(list);
    }

    /**
     * 将所有的文章数据放入ES中 只有系统管理员才可以操作
     * @return
     * @throws IOException
     */
    @GetMapping("putAllInES")
    @ApiOperation(value = "把所有的数据放入到ES中")
    @RequiresRoles(value = {"系统管理员"})
    public Result putAllInEs() throws IOException {
        BulkRequest req = new BulkRequest();
        List<Artical> allPass = articalService.getAllPass(new WillsPageHelper(1, 999));
        GetIndexRequest get = new GetIndexRequest("wills_blog");
        if(client.indices().exists(get,RequestOptions.DEFAULT)){
            // 先删除后创建
            DeleteIndexRequest d = new DeleteIndexRequest("wills_blog");
            client.indices().delete(d,RequestOptions.DEFAULT);
        }
        // 如果不存在就创建这个Index
        CreateIndexRequest create = new CreateIndexRequest("wills_blog");
        CreateIndexResponse response = client.indices().create(create, RequestOptions.DEFAULT);
        if(response.isAcknowledged()){
            for (Artical pass : allPass) {
                req.add(new IndexRequest("wills_blog").id(pass.getArticalId()+"").source(JSON.toJSONString(pass), XContentType.JSON));
            }
            BulkResponse bulk = client.bulk(req, RequestOptions.DEFAULT);
            return Result.buildSuccess(bulk.status());
        }
        return Result.buildServerError();
    }

    /**
     * 添加文章
     * @param artical
     * @return
     * @throws ParseException
     */
    @PutMapping("add")
    @ApiOperation(value = "添加文章")
    @RequiresRoles(value = {"系统管理员"})
    public Result add(@RequestBody Artical artical) throws ParseException {
        System.out.println(artical);
        if(artical.getArticalId() != 0) {
            articalService.update(artical);
        }else {
            // 如果articleId = 0 那么就是新增
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            artical.setPostTime(sdf.parse(sdf.format(d)));
            articalService.add(artical);
        }
        return Result.buildSuccess();
    }

    /**
     * 通过文章
     * @param id
     * @return
     */
    @PutMapping("/pass/{id}")
    @ApiOperation(value = "通过文章")
    @RequiresRoles(value = {"系统管理员"})
    public Result pass(@PathVariable("id") int id){
        articalService.pass(id);
        return Result.buildSuccess();
    }

    /**
     * 通过文章ID删除文章
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除文章")
    @RequiresRoles(value = {"系统管理员"})
    public Result delete(@PathVariable("id") int id){
        articalService.delete(id);
        return Result.buildSuccess();
    }

    /**
     * 修改文章
     * @param artical
     * @return
     */
    @PostMapping("update")
    @ApiOperation(value = "修改文章")
    @RequiresRoles(value = {"系统管理员"})
    public Result update(@RequestBody Artical artical){
        articalService.update(artical);
        return Result.buildSuccess();
    }

    /**
     * 获取所有文章
     * @param pageHelper
     * @return
     * @throws ParseException
     */
    @PostMapping("getAll")
    @ApiOperation(value = "获取所有文章")
    public Result getAll(@RequestBody WillsPageHelper pageHelper) throws ParseException {
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("allArticle",articalService.getAll(pageHelper));
        m.put("total",articalService.getAllCount());
        return Result.buildSuccess(m);
    }

    /**
     * 获取所有通过的文章
     * @param pageHelper
     * @return
     * @throws ParseException
     */
    @PostMapping("getAllPass")
    @ApiOperation(value = "获取所有通过的文章")
    public Result getAllPass(@RequestBody WillsPageHelper pageHelper) throws ParseException {
        log.info("获取所有的通过的文章");
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("allArticle",articalService.getAllPass(pageHelper));
        m.put("total",articalService.getAllPassCount());
        return Result.buildSuccess(m);
    }

    /**
     * 获取指定ID的文章
     * @param articalId
     * @return
     */
    @GetMapping("get/{id}")
    @ApiOperation(value = "获取指定ID的文章")
    public Result getArticalByPK(@PathVariable("id") int articalId){
        Artical a = articalService.getArticalByPK(articalId);
        return Result.buildSuccess(a);
    }

    /**
     * 修改文章的可读权限
     * @param articalId
     * @return
     */
    @PostMapping("changeStatus/{id}")
    @ApiOperation(value = "修改文章的可读权限")
    @RequiresRoles(value = {"系统管理员"})
    public Result changeArticalStatus(@PathVariable("id") int articalId){
        articalService.changeStatus(articalId);
        return Result.buildSuccess();
    }

    /**
     * 条件搜索文章
     * @param artical
     * @return
     */
    @PostMapping("getByCondition")
    @ApiOperation(value = "条件搜索文章")
    public Result getByCondition(@RequestBody Artical artical){
        Map<String, Object> m = new HashMap<>();
        List<Artical> list = articalService.getByCondition(artical);
        m.put("allArticle", list);
        m.put("total", list.size());
        return Result.buildSuccess(m);
    }

    /**
     * 分类下的文章
     * @param condition
     * @param willsPageHelper
     * @return
     */
    @PostMapping("getByCondition/{condition}")
    @ApiOperation(value = "分类下的文章")
    public Result get(@PathVariable("condition") String condition,@RequestBody WillsPageHelper willsPageHelper) {
        return Result.buildSuccess(articalService.get(willsPageHelper,condition));
    }

}
