package com.dwyanewang.dao;

import com.dwyanewang.entity.User;
import com.dwyanewang.entity.WorkOrder;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
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
    List<User> listUsersByTime(String startTime, String endTime);


    @InsertProvider(type = UserPrivider.class, method = "insertWorkOrder")
    int insert(List<WorkOrder> list);

    @SelectProvider(type = UserPrivider.class, method = "getCountsTwoTypesOfWorkOrder")
    List<WorkOrder> getCountsTwoTypesOfWorkOrder(String year, String month);

    @SelectProvider(type = UserPrivider.class, method = "getWorkOrderOfThreeSectionsDate")
    List<WorkOrder> getWorkOrderOfThreeSectionsDate(String year, String month);

    @SelectProvider(type = UserPrivider.class, method = "getWorkOrders")
    List<WorkOrder> getWorkOrders(String year, String month, String totalType);

    @Select("SELECT type, date AS statisticsDate, numbers, total_type AS totalType\n" +
            "FROM work_order")
    List<WorkOrder> getAllWorkOrders();

    @Select("select count(1) from work_order where date = #{date}")
    int hasDateOfWorkOrder(String date);

}
