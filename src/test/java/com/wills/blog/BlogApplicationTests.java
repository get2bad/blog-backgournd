package com.wills.blog;

import com.alibaba.fastjson.JSON;
import com.wills.blog.bean.Artical;
import com.wills.blog.bean.Tag;
import com.wills.blog.bean.User;
import com.wills.blog.dao.ArticalMapper;
import com.wills.blog.util.ActiveID;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpHost;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Log4j
class BlogApplicationTests {

     @Autowired
     @Qualifier("restHighLevelClient")
     private RestHighLevelClient client;

     @Autowired
     private ElasticsearchRestTemplate template;

     @Autowired
     private ArticalMapper articalMapper;

    @Test
    void createIndex() throws IOException {
        CreateIndexRequest req = new CreateIndexRequest("wills_index");
        CreateIndexResponse resp = client.indices().create(req, RequestOptions.DEFAULT);
        System.out.println(resp);
    }

    @Test
    void createIndexTemplate() throws IOException {
        boolean index = template.createIndex("wills_index");
        System.out.println(index);
    }

    @Test
    void getIndex() throws IOException {
        GetIndexRequest req = new GetIndexRequest("wills_index");
        boolean exists = client.indices().exists(req, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void getIndexTemplate() throws IOException {
        boolean index = template.indexExists("wills_index");
        System.out.println(index);
    }

    @Test
    void deleteEs() throws IOException {
        DeleteIndexRequest req = new DeleteIndexRequest("wills_index");
        AcknowledgedResponse delete = client.indices().delete(req, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    void deleteIndexTemplate() {
        boolean index = template.deleteIndex("wills_index");
        System.out.println(index);
    }

    @Test
    void insert() {
//        Map<String,Object> m = new HashMap<>();
//        m.put("userName",new Tag(1,"abc","anc"));
//        boolean b = template.putMapping("wills_index", "user", m);
//        System.out.println(b);
    }

    @Test
    void insertDoc() throws IOException {
        // 添加
        log.info("添加index中的内容");
        IndexRequest req = new IndexRequest("wills_index");
        req.id("1");
        req.timeout("100S");
        req.source(JSON.toJSONString(new Tag(1,"456","abc")), XContentType.JSON);
        IndexResponse resp = client.index(req,RequestOptions.DEFAULT);
        System.out.println(resp.toString());
        System.out.println(resp.status());
    }

    @Test
    void ifExists() throws IOException {
        //判断是否存在
        GetRequest get = new GetRequest("wills_index","1");
        get.fetchSourceContext(new FetchSourceContext(false));
        get.storedFields("_none_");
        boolean exists = client.exists(get, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //获取文档信息
    @Test
    void getDoc() throws IOException {
        GetRequest get = new GetRequest("wills_index","1");
        GetResponse response = client.get(get, RequestOptions.DEFAULT);
        System.out.println(JSON.parseObject(response.getSourceAsString()));
    }

    //获取文档信息
    @Test
    void updateDoc() throws IOException {
        UpdateRequest req = new UpdateRequest("wills_index", "1");
        req.doc(JSON.toJSONString(new Tag(2,"wills","隔壁老王")),XContentType.JSON);
        UpdateResponse update = client.update(req, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }

    //删除文档
    @Test
    void deleteDoc() throws IOException {
        DeleteRequest req = new DeleteRequest("wills_index","1");
        DeleteResponse resp = client.delete(req, RequestOptions.DEFAULT);
        System.out.println(resp);
    }

    // 批量插入文档
    @Test
    void bulkDoc() throws IOException {
        BulkRequest req = new BulkRequest();
        List<Artical> all = articalMapper.selectAll();
        for (Artical artical : all) {
            req.add(new IndexRequest("wills_index").id(artical.getArticalId()+"").source(JSON.toJSONString(artical),XContentType.JSON));
        }
        BulkResponse bulk = client.bulk(req, RequestOptions.DEFAULT);
        System.out.println(bulk);
    }


    // 查询
    @Test
    void search() throws IOException {
        SearchRequest req = new SearchRequest("wills_index");
        // 创建一个查询源
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //高亮显示
        HighlightBuilder highlighter = new HighlightBuilder();
        highlighter.field("articalTitle");
        highlighter.preTags("<span color:red;>");
        highlighter.postTags("</span>");
        builder.highlighter(highlighter);
        // 精确匹配
//        TermQueryBuilder term = QueryBuilders.termQuery("articalTitle", "博客测试标题");
        // 查询全部使用 QueryBuilders.matchAllQuery()
//        MatchAllQueryBuilder matchAll = QueryBuilders.matchAllQuery();
        // 查询包含某些信息使用一下代码
        MatchQueryBuilder math = QueryBuilders.matchQuery("articalTitle", "博客");
        SearchSourceBuilder builder1 = builder.query(math);
        // 设置查询的量
        builder.from(0);
        builder.size(100);
        // 设置查询时间
        builder.timeout(new TimeValue(20, TimeUnit.SECONDS));
        // 放入查询源
        req.source(builder);
        // 查询
        SearchResponse resp = client.search(req, RequestOptions.DEFAULT);
        // 输出查询到的信息
        System.out.println(JSON.toJSONString(resp.getHits()));
        for (SearchHit hit : resp.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
