package com.floatcloud.yucheng.dao;

import com.floatcloud.yucheng.enumeration.BlogFileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

/**
 * Blog 文档信息
 * @author floatcloud
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogFile {

    @NonNull
    private String title;
    @NonNull
    private int docId;
    private String creator;
    private Date createDate;
    private List<BlogFileTypeEnum> blogFileTypeEnumList;
    /**
     *  TODO 文件内容，有插图如何处理？
     */
    private String blogContext;


}
