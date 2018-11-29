package com.dwyanewang.service;

import java.util.Map;

/**
 * @FileName: UserService.java
 * @Description: TODO
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 18:00
 */
public interface UserService {
    Map<String, Object> listUsers(int page, int limit);
}
