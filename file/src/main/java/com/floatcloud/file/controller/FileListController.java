package com.floatcloud.file.controller;

import com.floatcloud.file.dao.FileBasic;
import com.floatcloud.file.response.BaseResponse;
import com.floatcloud.file.service.UserService;
import com.floatcloud.yucheng.constant.EurekaConstant;
import com.floatcloud.yucheng.dao.UserBasic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author floatcloud
 */
@Controller
@Slf4j
public class FileListController {


    @Resource
    private UserService userService;

    @ResponseBody
    @GetMapping("/basic/files")
    public BaseResponse getFileBasicList(){
        BaseResponse<List<FileBasic>> response = new BaseResponse<>();
        List<FileBasic> list = new ArrayList<>();
        // TODO 获取查询的文件列表
        response.setData(list);
        return response;
    }

    @ResponseBody
    @GetMapping("/new/files/{id}")
    public BaseResponse<List<FileBasic>> getReNewFileList(@PathVariable("id") int id){
        List<UserBasic> userBasicList = userService.getAttentionUsers(id);
        log.warn("获得的用户信息" + userBasicList.toString());
        // TODO 根据关注用户信息，获取用户更新情况
        BaseResponse<List<FileBasic>> response = new BaseResponse<>();
        return response;
    }
}
