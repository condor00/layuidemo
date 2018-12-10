package com.dwyanewang.controller;

import com.dwyanewang.entity.WorkOrder;
import com.dwyanewang.service.UserService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @FileName: UserController.java
 * @Description: 用户控制层
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 18:05
 */
@RestController
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;


    @PostMapping("/listUsersByTime")
    public Map<String, Object> listUsersByTime(@Param("page") int page, @Param("limit") int limit, @Param("startTime") String startTime, @Param("endTime") String endTime) {
        return userService.listUsersByTime(page, limit, startTime, endTime);
    }


    @GetMapping("/api/excption")
    public Object test() {
        int a = 1 / 0;
        return a;
    }

    @PostMapping("/insert")
    public int insert(@RequestParam("workOrders") String list) throws IOException {
        if (!list.equals("[]")) {
            //jackson对象
            ObjectMapper mapper = new ObjectMapper();
            //使用jackson将json转为List<User>
            JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, WorkOrder.class);
            List<WorkOrder> lists = mapper.readValue(list, jt);
            return userService.insert(lists);
        } else {
            return 0;
        }

    }
}
