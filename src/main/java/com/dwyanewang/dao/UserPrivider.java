package com.dwyanewang.dao;

import com.dwyanewang.entity.WorkOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.List;

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


    public String insertWorkOrder(List<WorkOrder> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO work_order ");
        sb.append("(type,date,numbers,total_type) ");
        sb.append("VALUES ");
        for (int i = 0; i < list.size(); i++) {
            sb.append("( ");
            sb.append("'").append(list.get(i).getType()).append("','").append(list.get(i).getDate()).append("',").append(list.get(i).getNumbers()).append(",'").append(list.get(i).getTotalType()).append("'");
            if (i == list.size() - 1) {
                sb.append(" )");
            } else {
                sb.append(" ),");
            }
        }
        return sb.toString();
    }
}
