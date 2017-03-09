package com.beijing.ocean.multmediademo.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/3/6.
 */
public class DbOpenHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "test.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "info";
    private static final String CREATE_INFO = "create table if not exists info("
            + "id integer primary key autoincrement,name varchar(20),"
            + "headurl varchar,age integer,sex varchar ,des varchar,userid varchar)";


    private SQLiteDatabase db;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         this.db=db;
         db.execSQL(CREATE_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
}
