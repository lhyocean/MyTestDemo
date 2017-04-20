package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.application.MyApplication;
import com.beijing.ocean.multmediademo.bean.User;
import com.beijing.ocean.multmediademo.greendao.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DaoMasterActivity extends Activity {
    @Bind(R.id.tv_insert)
    TextView tvInsert;
    @Bind(R.id.tv_update)
    TextView tvUpdate;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.tv_creat)
    TextView tvCreate;
    private UserDao mDao;

    private String[] names=new String[]{"小红","小明","DaoG"};
    private String[] sexs=new String[]{"男","女"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_master);
        ButterKnife.bind(this);

         mDao= MyApplication.getInstance().getDaoSession().getUserDao();

    }

    @OnClick({R.id.tv_creat,R.id.tv_insert,R.id.tv_search,R.id.tv_update})
    public void onClick(View view){

        switch (view.getId()) {
            case R.id.tv_insert:
                  insertUser();
                break;
            case R.id.tv_update:
                updateUser();
                break;
            case R.id.tv_search:
                logUser();
                break;
            case R.id.tv_creat:
               deleteUser(5l);
                break;
        }
    }

    private void deleteUser(Long id) {
        User u=new User();
        u.setName("小红");
        u.setId(19l);
        mDao.delete(u);
        mDao.deleteByKeyInTx(12l,16l);


    }

    private void logUser() {

        QueryBuilder<User> qb = mDao.queryBuilder();
        qb.where(UserDao.Properties.Name.eq("小红"), UserDao.Properties.Age.eq(19));



        List<User> list=mDao.loadAll();


        if (list!=null&&list.size()>0){
            Log.e("date----",""+list.toString());
        }else {
            Toast.makeText(DaoMasterActivity.this, "清茶如数据", Toast.LENGTH_SHORT).show();
        }


    }

    private void updateUser() {

        User u=new User();
        u.setName(names[0]);
        u.setSex(sexs[0]);
        u.setAge(66);
        u.setUserid("userID"+3);
        u.setDes("des-----");
        u.setHeadurl("");
        u.setId(10l);
        mDao.update(u);

    }

    private void insertUser() {
        for (int i = 0; i < 5; i++) {
            User u=new User();
            u.setName(names[(i%3)]);
            u.setSex(sexs[i%2]);
            u.setAge(19+i);
            u.setUserid("userID"+i);
            u.setDes("des-----");
            u.setHeadurl("");
            mDao.insert(u);
        }

    }

}
