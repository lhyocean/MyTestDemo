package com.beijing.ocean.multmediademo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ocean on 2017/4/17.
 */
@Entity
public class User0 {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String headurl;
    private String des;
    private int age;
    private String sex;
    private String  userid;
    private String cardNum;
    private String job;
    public String getJob() {
        return this.job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getCardNum() {
        return this.cardNum;
    }
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getHeadurl() {
        return this.headurl;
    }
    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1160148327)
    public User0(Long id, String name, String headurl, String des, int age,
            String sex, String userid, String cardNum, String job) {
        this.id = id;
        this.name = name;
        this.headurl = headurl;
        this.des = des;
        this.age = age;
        this.sex = sex;
        this.userid = userid;
        this.cardNum = cardNum;
        this.job = job;
    }
    @Generated(hash = 455077083)
    public User0() {
    }
}
