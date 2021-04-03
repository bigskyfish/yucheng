package com.floatcloud.file.enumeration;

/**
 * 根据博客标签类型来进行ES索引的创建
 * TODO 多个标签处理待续
 * @author floatcloud
 */

public enum ElasticSearchIndexEnum {
    BLOG_JAVA("blog_java"),
    BLOG_LIFE("blog_LIFE"),
    BLOG_TRAVEL("blog_travel");

    private String indexName;

    ElasticSearchIndexEnum(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexName() {
        return indexName;
    }
}
