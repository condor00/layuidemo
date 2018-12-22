package com.dwyanewang.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


public class WorkOrder {
    @JsonIgnore
    private long id;
    private String type;
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String statisticsDate;
    private long numbers;
    private String totalType;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public long getNumbers() {
        return numbers;
    }

    public void setNumbers(long numbers) {
        this.numbers = numbers;
    }


    public String getTotalType() {
        return totalType;
    }

    public void setTotalType(String totalType) {
        this.totalType = totalType;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "type='" + type + '\'' +
                ", statisticsDate='" + statisticsDate + '\'' +
                ", numbers=" + numbers +
                ", totalType='" + totalType + '\'' +
                '}';
    }
}
