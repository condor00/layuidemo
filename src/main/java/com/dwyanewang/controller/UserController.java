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
import java.util.*;

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

    @PostMapping("/getWorkOrder")
    public Map<String, Object> getWorkOrder(@Param("year") String year, @Param("month") String month) {
        Map<String, Object> map = new HashMap<>();
        map.put("twoTypesOfWorkOrder", CountsTwoTypesOfWorkOrder(year, month));
        map.put("threeSectionsDate", WorkOrderOfThreeSectionsDate(year, month));
        map.put("rushRepair", WorkOrder(year, month, "抢修工单"));
        map.put("noRushRepair", WorkOrder(year, month, "非抢修工单"));
        return map;
    }

    /**
     * 获取两类工单总数
     *
     * @param year  年份
     * @param month 月份
     * @return 对象
     */
    private Map<String, List<Object>> CountsTwoTypesOfWorkOrder(String year, String month) {
        Map<String, List<Object>> map = new HashMap<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<WorkOrder> workOrders = userService.getCountsTwoTypesOfWorkOrder(year, month);
        for (WorkOrder workOrder : workOrders) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("value", workOrder.getNumbers());
            map1.put("name", workOrder.getTotalType());
            list1.add(map1);
            list2.add(workOrder.getTotalType());

        }
        map.put("countData", list1);
        map.put("totalType", list2);
        return map;
    }

    /**
     * 获取三个日期段内的工单数量
     *
     * @param year  年份
     * @param month 月份
     * @return 对象
     */
    private Map<String, List<Object>> WorkOrderOfThreeSectionsDate(String year, String month) {
        Map<String, List<Object>> map = new HashMap<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<Object> list3 = new ArrayList<>();
        List<Object> list4 = new ArrayList<>();
        List<WorkOrder> workOrders = userService.getWorkOrderOfThreeSectionsDate(year, month);
        for (WorkOrder workOrder : workOrders) {
            if (workOrder.getTotalType().equals("抢修工单")) {

                list1.add(workOrder.getNumbers());
            } else {
                System.out.println(workOrder.getNumbers());
                list2.add(workOrder.getNumbers());
            }
            list3.add(workOrder.getTotalType());
            list4.add(workOrder.getStatisticsDate());
        }
        list3 = new ArrayList<>(new LinkedHashSet<>(list3));
        list4 = new ArrayList<>(new LinkedHashSet<>(list4));
        map.put("rushRepair", list1);
        map.put("noRushRepair", list2);
        map.put("totalType", list3);
        map.put("statisticsDate", list4);
        return map;
    }

    /**
     * 获取工单各类型的数量
     *
     * @param year      年份
     * @param month     月份
     * @param totalType 工单类型
     * @return 对象
     */
    private Map<String, List<Object>> WorkOrder(String year, String month, String totalType) {
        Map<String, List<Object>> map = new HashMap<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<WorkOrder> workOrders = userService.getWorkOrders(year, month, totalType);
        for (WorkOrder workOrder : workOrders) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("value", workOrder.getNumbers());
            map1.put("name", workOrder.getType());
            list1.add(map1);
            list2.add(workOrder.getType());
        }
        map.put("countData", list1);
        map.put("type", list2);
        return map;
    }


    @PostMapping("/hasDateOfWorkOrder")
    public int hasDateOfWorkOrder(@Param("date") String date) {
        return userService.hasDateOfWorkOrder(date);
    }


    @PostMapping("/getCountsTwoTypesOfWorkOrder")
    public List<WorkOrder> getCountsTwoTypesOfWorkOrder(@Param("year") String year, @Param("month") String month) {
        return userService.getCountsTwoTypesOfWorkOrder(year, month);
    }


    @PostMapping("/getWorkOrderOfThreeSectionsDate")
    public List<WorkOrder> getWorkOrderOfThreeSectionsDate(@Param("year") String year, @Param("month") String month) {
        return userService.getWorkOrderOfThreeSectionsDate(year, month);
    }


    @PostMapping("/getWorkOrderOfRushRepair")
    public List<WorkOrder> getWorkOrderOfRushRepair(@Param("year") String year, @Param("month") String month) {
        return userService.getWorkOrders(year, month, "抢修工单");
    }

    @PostMapping("/getWorkOrderOfNoRushRepair")
    public List<WorkOrder> getWorkOrderOfNoRushRepair(@Param("year") String year, @Param("month") String month) {
        return userService.getWorkOrders(year, month, "非抢修工单");
    }

    @PostMapping("/getAll")
    public List<WorkOrder> getAllWorkOrders() {
        return userService.getAllWorkOrders();
    }
}
