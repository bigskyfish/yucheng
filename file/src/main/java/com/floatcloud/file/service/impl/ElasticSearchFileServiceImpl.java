package com.floatcloud.file.service.impl;

import com.alibaba.fastjson.JSON;
import com.floatcloud.file.enumeration.ElasticSearchIndexEnum;
import com.floatcloud.file.response.BaseResponse;
import com.floatcloud.file.service.ElasticSearchFileService;
import com.floatcloud.yucheng.dao.BlogFile;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return addBlogFile(searchIndexEnum, null,null, blogFile);
    }

    /**
     * ES 中添加Blog 文档信息
     * @param searchIndexEnum 文档的Index
     * @param type 类型
     * @param timeOut 超时时间
     * @param blogFile Blog文档
     * @return 标准返回BaseResponse
     */
    public BaseResponse addBlogFile(ElasticSearchIndexEnum searchIndexEnum, String type, Long timeOut,BlogFile blogFile) {
        IndexRequest request = new IndexRequest(searchIndexEnum.getIndexName())
                .id(String.valueOf(blogFile.getDocId())).source(JSON.toJSONString(blogFile), XContentType.JSON);
        // 默认Type 为"_doc"
        request.opType(type != null && !type.isEmpty() ? type : "_doc");
        // 设置请求的超时时间(默认为1s)
        request.timeout(TimeValue.timeValueSeconds(timeOut != null && timeOut > 0 ? timeOut : 1));
        BaseResponse<IndexResponse> baseResponse = new BaseResponse();
        try {
            IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            if(index.getResult() == DocWriteResponse.Result.CREATED){
                // 创建成功
                baseResponse.setData(index);
            } else {
                // TODO 这里如果DocId冲突，则会触发修改操作
                baseResponse.setStatus(0);
                baseResponse.setErrorCode(index.getResult().getOp());
            }
        } catch (IOException e){
            baseResponse.setStatus(0);
            baseResponse.setMsg(e.getMessage());
            log.error("ES add Index Error!", e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateBlogFile(ElasticSearchIndexEnum searchIndexEnum, BlogFile blogFile) {
        return updateBlogFile(searchIndexEnum, null, blogFile);
    }


    /**
     * ES 中修改Blog 文档信息
     * @param searchIndexEnum 文档的Index
     * @param timeOut 超时时间
     * @param blogFile Blog文档
     * @return 标准返回BaseResponse
     */
    public BaseResponse updateBlogFile(ElasticSearchIndexEnum searchIndexEnum, Long timeOut, BlogFile blogFile) {
        BaseResponse<UpdateResponse> baseResponse = new BaseResponse();
        UpdateRequest updateRequest = new UpdateRequest(searchIndexEnum.getIndexName(), String.valueOf(blogFile.getDocId()));
        updateRequest.timeout(TimeValue.timeValueSeconds(timeOut != null && timeOut > 0 ? timeOut : 1));
        try {
            UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            DocWriteResponse.Result result = update.getResult();
            switch(result){
                case CREATED:
                    // ID 不存在创建
                    baseResponse.setStatus(0);
                    baseResponse.setMsg("创建");
                    break;
                case DELETED:
                    // 已删除
                    baseResponse.setStatus(0);
                    baseResponse.setMsg("已删除");
                    break;
                case UPDATED:
                    // 修改成功
                    baseResponse.setStatus(1);
                case NOT_FOUND:
                    // 未找到
                case NOOP:
                default:
                    break;
            }
            baseResponse.setData(update);
        } catch (IOException e){
            baseResponse.setStatus(0);
            baseResponse.setMsg(e.getMessage());
            log.error("updateBlogFile is Error!", e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteBlogFile(ElasticSearchIndexEnum searchIndexEnum, int docId) {
        return deleteBlogFile(searchIndexEnum, null, docId);
    }

    /**
     * ES 中删除 Blog 记录
     * @param searchIndexEnum 文档的Index
     * @param timeOut 超时时间
     * @param docId 文档ID
     * @return 标准返回BaseResponse
     */
    public BaseResponse deleteBlogFile(ElasticSearchIndexEnum searchIndexEnum, Long timeOut, int docId) {
        BaseResponse baseResponse = new BaseResponse();
        DeleteRequest deleteRequest = new DeleteRequest(searchIndexEnum.getIndexName(), String.valueOf(docId));
        deleteRequest.timeout(TimeValue.timeValueMinutes(timeOut != null && timeOut > 0 ? timeOut : 1));
        try {
            DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            ReplicationResponse.ShardInfo shardInfo = delete.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()){

            } else if (shardInfo.getFailed() > 0)  {
                StringBuilder stringBuilder = new StringBuilder();
                for(ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()){
                    stringBuilder.append(failure.reason());
                }
                baseResponse.setStatus(0);
                baseResponse.setMsg(stringBuilder.toString());
            }
            baseResponse.setData(delete);
        } catch (IOException e){
            baseResponse.setStatus(0);
            baseResponse.setMsg(e.getMessage());
            log.error("updateBlogFile is Error!", e);
        }
        return baseResponse;
    }


    @Override
    public BaseResponse queryBlogFile(ElasticSearchIndexEnum searchIndexEnum, String title) {
        return queryBlogFile(searchIndexEnum, null, title);
    }

    /**
     * 根据Blog 标题进行搜索
     * TODO 多条件查询
     * @param searchIndexEnum 文档的Index
     * @param timeOut 超时时间
     * @param title 文档标题
     * @return 标准返回BaseResponse
     */
    public BaseResponse queryBlogFile(ElasticSearchIndexEnum searchIndexEnum, Long timeOut, String title) {
        BaseResponse<List<Map<String,Object>>> baseResponse = new BaseResponse();
        SearchRequest searchRequest = new SearchRequest(searchIndexEnum.getIndexName());
        // 查询搜索条件Builder的创建
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("title", title);

        sourceBuilder.timeout(TimeValue.timeValueSeconds(timeOut != null && timeOut > 10 ? timeOut : 10));
        sourceBuilder.query(queryBuilder);
        // 填充查询条件
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            List<Map<String,Object>> resultList = new ArrayList<>(20);
            for (SearchHit searchHit : hits) {
                resultList.add(searchHit.getSourceAsMap());
            }
            baseResponse.setData(resultList);
        } catch (IOException e){
            baseResponse.setStatus(0);
            baseResponse.setMsg(e.getMessage());
            log.error("queryBlogFile is Error!", e);
        }
        return null;
    }
}
