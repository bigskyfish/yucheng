package com.floatcloud.file.service.impl;

import com.floatcloud.file.enumeration.ElasticSearchIndexEnum;
import com.floatcloud.file.response.BaseResponse;
import com.floatcloud.file.service.ElasticSearchFileService;
import com.floatcloud.yucheng.dao.BlogFile;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.stereotype.Service;

import java.io.IOException;

import javax.annotation.Resource;

/**
 * ES 文件服务：文档的添加
 * @author floatcloud
 */
@Service
@Slf4j
public class ElasticSearchFileServiceImpl implements ElasticSearchFileService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public BaseResponse addBlogFile(ElasticSearchIndexEnum searchIndexEnum, BlogFile blogFile) {
        return addBlogFile(searchIndexEnum, null, blogFile);
    }

    public BaseResponse addBlogFile(ElasticSearchIndexEnum searchIndexEnum, String type, BlogFile blogFile) {
        IndexRequest request = new IndexRequest(searchIndexEnum.getIndexName())
                .id(String.valueOf(blogFile.getDocId())).timeout(TimeValue.timeValueSeconds(1)).source(blogFile);
        if (type!= null && type.isEmpty()) {
            request.opType(type);
        }
        BaseResponse<IndexResponse> baseResponse = new BaseResponse();
        try {
            IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            if(index.getResult() == DocWriteResponse.Result.CREATED){
                // 创建成功
                baseResponse.setData(index);
            } else {
                baseResponse.setStatus(0);
                baseResponse.setErrorCode(index.getResult().getOp());
            }
        } catch (IOException e){
            baseResponse.setStatus(0);
            log.error("ES add Index Error!", e);
        }
        return baseResponse;
    }

}
