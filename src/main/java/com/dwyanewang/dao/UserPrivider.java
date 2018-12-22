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
            sb.append("'").append(list.get(i).getType()).append("','").append(list.get(i).getStatisticsDate()).append("',").append(list.get(i).getNumbers()).append(",'").append(list.get(i).getTotalType()).append("'");
            if (i == list.size() - 1) {
                sb.append(" )");
            } else {
                sb.append(" ),");
            }
        }
        sb.append("ON DUPLICATE KEY UPDATE type = values(type),date = values(date),numbers = values(numbers),total_type = values(total_type)");
        return sb.toString();
    }

    /**
     * 获取两类工单总数
     *
     * @param year  年份
     * @param month 月份
     * @return String sql语句
     */
    public String getCountsTwoTypesOfWorkOrder(String year, String month) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT SUM(numbers) AS numbers, total_type AS totalType ");
        sb.append(" FROM work_order ");
        if (year != null && !year.equals("")) {
            if (month != null && !month.equals("")) {
                sb.append(" WHERE date >= '").append(year).append("-").append(month).append("-01' ");
                sb.append(" AND date <= LAST_DAY('").append(year).append("-").append(month).append("-01') ");
            } else {
                sb.append(" WHERE date >= '").append(year).append("-01-01' ");
                sb.append(" AND date <= '").append(year).append("-12-31' ");
            }
        } else {
            if (month == null || month.equals("")) {
                sb.append(" WHERE date >= concat(YEAR(now()),'-01-01') ");
                sb.append(" AND date <= concat(YEAR(now()),'-12-31') ");
            }
        }
        sb.append(" GROUP BY totalType ");
        return sb.toString();
    }

    /**
     * 获取三个日期段内的工单数量
     *
     * @param year  年份
     * @param month 月份
     * @return String sql语句
     */
    public String getWorkOrderOfThreeSectionsDate(String year, String month) {
        StringBuilder sb = new StringBuilder();
        if (year != null && !year.equals("")) {
            String yearsAgo = String.valueOf(Integer.parseInt(year) - 2);
            if (month != null && !month.equals("")) {
                sb.append(" SELECT date_format(date,'%Y-%m') AS statisticsDate, SUM(numbers) AS numbers, total_type AS totalType ");
                sb.append(" FROM work_order ");
                sb.append(" WHERE date <= LAST_DAY('").append(year).append("-").append(month).append("-01') AND date >= date_sub('").append(year).append("-").append(month).append("-01',interval 2 month) ");
            } else {
                sb.append(" SELECT YEAR(date) AS statisticsDate, SUM(numbers) AS numbers, total_type AS totalType ");
                sb.append(" FROM work_order ");
                sb.append(" WHERE date <= '").append(year).append("-12-31' AND date >= '").append(yearsAgo).append("-01-01' ");
            }
        } else {
            if (month == null || month.equals("")) {
                sb.append(" SELECT YEAR(date) AS statisticsDate, SUM(numbers) AS numbers, total_type AS totalType ");
                sb.append(" FROM work_order ");
                sb.append(" WHERE date <= concat(YEAR(now()),'-12-31') AND date >= concat(YEAR(now())-2, '-01-01') ");
            }
        }
        sb.append(" GROUP BY statisticsDate, totalType ");
        return sb.toString();
    }

//    /**
//     * 获取抢修工单各类型的数量
//     * @param year 年份
//     * @param month 月份
//     * @return String sql语句
//     */
//    public String getWorkOrderOfRushRepair(String year, String month) {
//        StringBuilder sb = new StringBuilder();
//        if (year != null && !year.equals("")) {
//            if (month != null && !month.equals("")) {
//                sb.append(" SELECT type, numbers ");
//                sb.append(" FROM work_order ");
//                sb.append(" WHERE date >= '").append(year).append("-").append(month).append("-01' ");
//                sb.append(" AND date <= LAST_DAY('").append(year).append("-").append(month).append("-01') ");
//                sb.append(" AND total_type = '抢修工单' ");
//            } else {
//                sb.append(" SELECT type, SUM(numbers) AS numbers ");
//                sb.append(" FROM work_order ");
//                sb.append(" WHERE date >= '").append(year).append("-01-01' ");
//                sb.append(" AND date <= '").append(year).append("-12-31' ");
//                sb.append(" AND total_type = '抢修工单' ");
//                sb.append(" GROUP BY type ");
//            }
//        } else {
//            if (month == null || month.equals("")) {
//                sb.append(" SELECT type, SUM(numbers) AS numbers ");
//                sb.append(" FROM work_order ");
//                sb.append(" WHERE date >= concat(YEAR(now()),'-01-01') ");
//                sb.append(" AND date <= concat(YEAR(now()),'-12-31') ");
//                sb.append(" AND total_type = '抢修工单' ");
//                sb.append(" GROUP BY type ");
//            }
//        }
//        return sb.toString();
//    }

    /**
     * 获取工单各类型的数量
     * @param year 年份
     * @param month 月份
     * @param totalType 工单类型
     * @return
     */
    public String getWorkOrders(String year, String month, String totalType) {
        StringBuilder sb = new StringBuilder();
        if (year != null && !year.equals("")) {
            if (month != null && !month.equals("")) {
                sb.append(" SELECT type, numbers ");
                sb.append(" FROM work_order ");
                sb.append(" WHERE date >= '").append(year).append("-").append(month).append("-01' ");
                sb.append(" AND date <= LAST_DAY('").append(year).append("-").append(month).append("-01') ");
                sb.append(" AND total_type = '").append(totalType).append("' ");
            } else {
                sb.append(" SELECT type, SUM(numbers) AS numbers ");
                sb.append(" FROM work_order ");
                sb.append(" WHERE date >= '").append(year).append("-01-01' ");
                sb.append(" AND date <= '").append(year).append("-12-31' ");
                sb.append(" AND total_type = '").append(totalType).append("' ");
                sb.append(" GROUP BY type ");
            }
        } else {
            if (month == null || month.equals("")) {
                sb.append(" SELECT type, SUM(numbers) AS numbers ");
                sb.append(" FROM work_order ");
                sb.append(" WHERE date >= concat(YEAR(now()),'-01-01') ");
                sb.append(" AND date <= concat(YEAR(now()),'-12-31') ");
                sb.append(" AND total_type = '").append(totalType).append("' ");
                sb.append(" GROUP BY type ");
            }
        }
        return sb.toString();
    }
}
