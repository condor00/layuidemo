package com.dwyanewang.controller;

import com.dwyanewang.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @FileName: UserController.java
 * @Description: 用户控制层
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 18:05
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping("/listUsersByTime")
    public Map<String, Object> listUsersByTime(@Param("page") int page, @Param("limit") int limit, @Param("startTime") String startTime, @Param("endTime") String endTime) {
        return userService.listUsersByTime(page, limit, startTime, endTime);
    }
}
