package com.beijing.ocean.multmediademo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ocean on 2017/4/17.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String headurl;
    private String des;
    private int age;
    private String sex;
    private String  userid;
    private String cardNum;


    @Generated(hash = 1741387292)
    public User(Long id, String name, String headurl, String des, int age,
            String sex, String userid, String cardNum) {
        this.id = id;
        this.name = name;
        this.headurl = headurl;
        this.des = des;
        this.age = age;
        this.sex = sex;
        this.userid = userid;
        this.cardNum = cardNum;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headurl='" + headurl + '\'' +
                ", des='" + des + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", userid='" + userid + '\'' +
                ", cardNum='" + cardNum + '\'' +
                '}'+"\n";
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


    public String getCardNum() {
        return this.cardNum;
    }


    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }


}
