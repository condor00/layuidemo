package com.dwyanewang.service.impl;

import com.dwyanewang.dao.UserMapper;
import com.dwyanewang.entity.User;
import com.dwyanewang.entity.WorkOrder;
import com.dwyanewang.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName: UserServiceImpl.java
 * @Description: 用户服务层接口实现类
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 18:01
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> listUsersByTime(int page, int limit, String startTime, String endTime) {
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.listUsersByTime(startTime, endTime);
        long count = ((Page<User>) users).getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", 0);
        map.put("msg", "获取数据成功");
        map.put("count", count);
        map.put("data", users);
        return map;
    }

    @Override
    public int insert(List<WorkOrder> list) {
        return userMapper.insert(list);
    }
}
