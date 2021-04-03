package com.floatcloud.user.controller;


import com.floatcloud.yucheng.dao.UserBasic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @author floatcloud
 */
@Controller
public class UserController {

    @ResponseBody
    @GetMapping("/users/{id}")
    public List<UserBasic> getUserList(@PathVariable("id") int id){
        List<UserBasic> userBasicList = new ArrayList<>();
        // TODO 获取指定UserId关注的所有微博用户
        System.out.println("获取关注的博客用户成功");
        return userBasicList;
    }


}
