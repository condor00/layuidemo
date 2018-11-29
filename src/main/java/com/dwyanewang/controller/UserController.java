package com.dwyanewang.controller;

import com.dwyanewang.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @FileName: UserController.java
 * @Description: TODO
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 18:05
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;



    @GetMapping("/listUsers")
    public Map<String, Object> listUsers(int page, int limit){
        return userService.listUsers(page, limit);
    }
}
