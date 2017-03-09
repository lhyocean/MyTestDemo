package com.beijing.ocean.multmediademo.bean;

/**
 * Created by admin on 2016/11/12.
 */
public class UserInfo {
    private String name;
    private String headurl;
    private String des;
    private int age;
    private String sex;
    private String  userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", headurl='" + headurl + '\'' +
                ", des='" + des + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", userid='" + userid + '\'' +
                '}'+"\n";
    }
}
