package com.beijing.ocean.multmediademo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.Selection;
import android.util.Log;

import com.beijing.ocean.multmediademo.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/6.
 */
public class MyDbUtils {
    private  DbOpenHelper mHelper;
    private Context mContext;
    public  static SQLiteDatabase database;

    public MyDbUtils(Context context) {
        mContext = context;

         if (mHelper==null){
             mHelper=new DbOpenHelper(context);
             database=mHelper.getWritableDatabase();
         }
    }


    public long insert(UserInfo smsEntity)
    {
            database=mHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("userid", smsEntity.getUserid());
            values.put("name",  smsEntity.getName());
            values.put("des",  smsEntity.getDes());
            values.put("headurl", smsEntity.getHeadurl());
            values.put("age",smsEntity.getAge());
            values.put("sex", smsEntity.getSex());
            return database.insert(DbOpenHelper.TABLE_NAME, null, values);

        }catch (SQLException e){

              database.close();
            return -1;
        }finally {

        }

    }

    public  void update(String id,ContentValues values){

        try {
            database=mHelper.getWritableDatabase();
            database.update(DbOpenHelper.TABLE_NAME,values,"userid=?",new String[]{id});
            database.close();
        }catch (SQLException e){
            database.close();
        }
    }


    public  void update(String sql){

        try {
            database=mHelper.getWritableDatabase();
            database.execSQL(sql);
            database.close();
        }catch (SQLException e){
            database.close();
        }
    }


    public List<UserInfo> getAllUser(){
        List<UserInfo> list=new ArrayList<>();

            String sql="select * from "+DbOpenHelper.TABLE_NAME;

        try {
            database=mHelper.getWritableDatabase();
            Cursor cursor= database.rawQuery(sql,null);

            while (cursor.moveToNext()){
                UserInfo info=new UserInfo();
                info.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                info.setDes(cursor.getString(cursor.getColumnIndex("des")));
                info.setName(cursor.getString(cursor.getColumnIndex("name")));
                info.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                info.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                info.setHeadurl(cursor.getString(cursor.getColumnIndex("headurl")));
                list.add(info);
            }
            database.close();
        }catch (SQLException e){
            database.close();
        }
        Log.e("getAllUser", "getAllUser: " +list.toString());
        return list;
    }

    public List<UserInfo> getSelectUser(){
        List<UserInfo> list=new ArrayList<>();

        String sql="select * from "+DbOpenHelper.TABLE_NAME;

        try {
            database=mHelper.getWritableDatabase();

            Cursor cursor= database.query(DbOpenHelper.TABLE_NAME,null, "age=? and name=? and sex=?",new String[]{"1","李四","女"},null,null,null);

            while (cursor.moveToNext()){
                UserInfo info=new UserInfo();
                info.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                info.setDes(cursor.getString(cursor.getColumnIndex("des")));
                info.setName(cursor.getString(cursor.getColumnIndex("name")));
                info.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                info.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                info.setHeadurl(cursor.getString(cursor.getColumnIndex("headurl")));
                list.add(info);
            }
            database.close();
        }catch (SQLException e){
            database.close();
        }
        Log.e("getAllUser", "getAllUser: " +list.toString());
        return list;
    }
}
