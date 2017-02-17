package com.beijing.ocean.multimediademo.bean;

import java.util.List;

/**
 * Created by admin on 2016/11/23.
 */
public class MyBean {
    private     String name;
    private List<GoodBean> mList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodBean> getList() {
        return mList;
    }

    public void setList(List<GoodBean> list) {
        mList = list;
    }
}
