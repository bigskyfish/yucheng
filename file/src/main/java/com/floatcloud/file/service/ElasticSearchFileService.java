package com.floatcloud.file.service;

import com.floatcloud.file.enumeration.ElasticSearchIndexEnum;
import com.floatcloud.file.response.BaseResponse;
import com.floatcloud.yucheng.dao.BlogFile;

/**
 * ES 常用操作
 * @author floatcloud
 */
public interface ElasticSearchFileService {


    /**
     * 在ES指定Index中创建Document
     * @param searchIndexEnum Index名
     * @param blogFile Blog 文档信息
     * @return 返回结构
     */
    BaseResponse addBlogFile(ElasticSearchIndexEnum searchIndexEnum, BlogFile blogFile);


    /**
     * ES中Blog 文档信息修改
     * @param searchIndexEnum Index名称
     * @param blogFile Blog 文档信息
     * @return 标准返回
     */
    BaseResponse updateBlogFile(ElasticSearchIndexEnum searchIndexEnum, BlogFile blogFile);


    /**
     * ES中 Blog 文档信息删除
     * @param searchIndexEnum Index名称
     * @param docId 文档id
     * @return BaseResponse
     */
    BaseResponse deleteBlogFile(ElasticSearchIndexEnum searchIndexEnum, int docId);

    /**
     * ES中 查询 Blog 信息
     * @param searchIndexEnum Index名称
     * @param title  标题
     * @return BaseResponse
     */
    BaseResponse queryBlogFile(ElasticSearchIndexEnum searchIndexEnum, String title);

}
