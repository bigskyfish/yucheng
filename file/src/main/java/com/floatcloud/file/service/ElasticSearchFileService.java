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
     * @return 返回结构
     */
    BaseResponse addBlogFile(ElasticSearchIndexEnum searchIndexEnum, BlogFile blogFile);

}
