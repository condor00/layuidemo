package com.dwyanewang.dao;

import com.dwyanewang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @FileName: UserMapper.java
 * @Description: 用户数据访问层
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 17:55
 */
@Mapper
public interface UserMapper {

    @SelectProvider(type = UserPrivider.class, method = "searchUsers")
    List<User> listUsersByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
