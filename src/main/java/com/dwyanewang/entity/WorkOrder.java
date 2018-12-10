package com.dwyanewang.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;


public class WorkOrder {

    private long id;
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
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


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", date='" + date + '\'' +
                ", numbers=" + numbers +
                ", totalType='" + totalType + '\'' +
                '}';
    }
}
