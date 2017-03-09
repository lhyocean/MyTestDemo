package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.UserInfo;
import com.beijing.ocean.multmediademo.utils.DbOpenHelper;
import com.beijing.ocean.multmediademo.utils.MyDbUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SQLiteActivity extends Activity {

    @Bind(R.id.tv_creat)
    TextView tvCreate;
    @Bind(R.id.tv_insert)
    TextView tvInsert;
    @Bind(R.id.tv_update)
    TextView tvUpdate;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    private MyDbUtils mDbUtils;
    private String[] sexs=new String[]{"男","女"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.bind(this);

        mDbUtils=new MyDbUtils(this);


    }
    @OnClick({R.id.tv_creat,R.id.tv_insert,R.id.tv_update,R.id.tv_search})
    public  void  onClick(View view){
        switch (view.getId()) {

            case R.id.tv_creat:
                mDbUtils.getSelectUser();

                break;
            case R.id.tv_insert:

                insertUSerinfo();


                break;
            case R.id.tv_update:
                updateBean();

                break;
            case R.id.tv_search:

                mDbUtils.getAllUser();
                break;


        }

    }

    private void updateBean() {



//        String sql="delete from "+ DbOpenHelper.TABLE_NAME +" where name =  '李四' or name =  '张三'";
        String sql="update "+ DbOpenHelper.TABLE_NAME +" set sex = '女' where name = '汪汪'";
        mDbUtils.update(sql);

    }

    private void insertUSerinfo() {

        String userid=new Random().nextInt(1000)+"我是userid";
        int age =new Random().nextInt(3);
        String name = Commen.NAMES[new Random().nextInt(3)];
        String url=Commen.PHOTOS[new Random().nextInt(10)];

        UserInfo info=new UserInfo();
        info.setHeadurl(url);
        info.setName(name);
        info.setAge(age);
        info.setDes(Commen.CONTENTS[new Random().nextInt(10)]);
        info.setUserid(userid);
        info.setSex(sexs[(new Random().nextInt(100))%2]);
        mDbUtils.insert(info);
    }





}
