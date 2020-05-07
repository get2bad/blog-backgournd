package com.wills.blog.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticSearchConfig {

    @Value("${wills.server.ip}")
    private String serverIp;

    @Value("${wills.server.elasticsearch.port}")
    private int esPort;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
         RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(serverIp, esPort, "http")));
         return client;
    }

    @Bean
    public ElasticsearchRestTemplate template() {
        ElasticsearchRestTemplate template = new ElasticsearchRestTemplate(restHighLevelClient());
        return template;
    }
}
