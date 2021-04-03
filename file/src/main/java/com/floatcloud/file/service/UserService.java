package com.floatcloud.file.service;

import com.floatcloud.yucheng.constant.EurekaConstant;
import com.floatcloud.yucheng.dao.UserBasic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author floatcloud
 */
@Service
@FeignClient(value= "USER-MANAGE")
public interface UserService {

    /**
     * 获取所有关注的博客账户
     * @param id userID
     * @return 返回
     */
    @GetMapping("/users/{id}")
    List<UserBasic> getAttentionUsers(@PathVariable("id") int id);
}
