package com.floatcloud.yucheng.enumeration;

/**
 * 博文文件标签
 * @author floatcloud
 */
public enum BlogFileTypeEnum {

    BLOG_JAVA("java"),
    BLOG_LIFE("生活"),
    BLOG_TWO_DIMENSION("二次元");

    private String typeName;

    BlogFileTypeEnum(String typeName) {
        this.typeName = typeName;
    }
}
