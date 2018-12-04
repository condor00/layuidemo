package com.dwyanewang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @FileName: User.java
 * @Description: 用户实体类
 * @Author: Dwyanewang
 * @CreateTime: 2018/11/29 17:55
 */
public class User {

    private long id;
    private String name;
    private long experience;
    private String sex;
    private long score;
    private String city;
    private String sign;
    private String classify;
    private long wealth;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date faultDate;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dealDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }


    public long getWealth() {
        return wealth;
    }

    public void setWealth(long wealth) {
        this.wealth = wealth;
    }

    public Date getFaultDate() {
        return faultDate;
    }

    public void setFaultDate(Date faultDate) {
        this.faultDate = faultDate;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
}
