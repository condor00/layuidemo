package com.dwyanewang.dao;

import com.dwyanewang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @FileName: UserMapper.java
 * @Description: TODO
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 17:55
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> listUsers();


}
