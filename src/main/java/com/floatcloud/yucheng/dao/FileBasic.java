package com.floatcloud.yucheng.dao;

import com.floatcloud.yucheng.enumeration.FileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author floatcloud
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileBasic {

    private int fileId;

    private String fileName;

    private FileTypeEnum fileTypeEnum;

}