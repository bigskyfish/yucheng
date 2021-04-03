package com.floatcloud.file.configuration;

import com.floatcloud.file.constant.ElasticSearchConstant;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 进行
 * @author floatcloud
 */
@Configuration
public class ElasticSearchConfiguration {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost(ElasticSearchConstant.ES_CLUSTER_ADDRESS,
                ElasticSearchConstant.ES_CLUSTER_PORT,"http")));
    }


}
