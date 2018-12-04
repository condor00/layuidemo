package com.dwyanewang.service;

import com.dwyanewang.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @FileName: UserService.java
 * @Description: 用户服务层接口
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 18:00
 */
public interface UserService {

    Map<String, Object> listUsersByTime(int page, int limit, String startTime, String endTime);
}
