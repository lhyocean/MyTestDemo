package com.beijing.ocean.multmediademo.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

/**
 * Created by ocean on 2017/4/17.
 */
public class MyDbOpenHelper extends DaoMaster.OpenHelper{
    public MyDbOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db,UserDao.class);
    }
}
