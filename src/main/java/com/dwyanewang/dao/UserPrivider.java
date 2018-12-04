package com.dwyanewang.dao;

import org.apache.ibatis.jdbc.SQL;

/**
 * @FileName: UserPrivider.java
 * @Description: 用户数据访问层，访问语句类
 * @Author: Dwyanewang
 * @CreateTime: 2018/12/3 23:42
 */
public class UserPrivider {
    public String searchUsers(String startTime, String endTime) {
        String sql = new SQL() {{
            SELECT("*");
            FROM("user");
            WHERE("1=1");
            if (startTime != null && !startTime.equals("")) {
                AND();
                WHERE("faultDate >= '" + startTime + "'");  //faultDate >= 2018-04-04 => faultDate >= '2018-04-04'
            }
            if (endTime != null && !endTime.equals("")) {
                AND();
                WHERE("faultDate <= '" + endTime + "'");
            }
        }}.toString();
        return sql;
    }
}
