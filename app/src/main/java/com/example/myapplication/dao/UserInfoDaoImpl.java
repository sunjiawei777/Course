package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.entity.UserInfo;
import com.example.myapplication.utils.DBHelper;

import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao{
    private DBHelper helper;//用户创建数据库。获取数据库对象
    private SQLiteDatabase db;//用于执行sql语句

    public UserInfoDaoImpl(Context context){
        helper=DBHelper.getInstance(context);
    }

    @Override
    public List<UserInfo> select() {
        return null;
    }

    @Override
    public UserInfo select(String username) {
        String sql = "select * from tb_user where user_name=?";
        UserInfo userInfo = null;
        db = helper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TBL_NAME_USER,null,
                "user_name=?",new String[]{username},
                null,null,null);
//        Cursor cursor = db.rawQuery(sql, new String[]{username});
        if (cursor !=null && cursor.moveToFirst()){
            userInfo = new UserInfo();
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex("user_name")));
            userInfo.setNickname(cursor.getString(cursor.getColumnIndex("nick_name")));
            userInfo.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            userInfo.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
            cursor.close();
        }
        db.close();
        return userInfo;
    }

    @Override
    public void insert(UserInfo userInfo) {
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("user_name",userInfo.getUsername());
        values.put("nick_name",userInfo.getNickname());
        values.put("sex",userInfo.getSex());
        values.put("signature",userInfo.getSignature());
        db.insert(DBHelper.TBL_NAME_USER,null,values);

//        String sql = "insert into " + DBHelper.TBL_NAME_USER + " values(null,?,?,?,?)";
//        db.execSQL(sql,new String[]{
//                userInfo.getUsername(),
//                userInfo.getNickname(),
//                userInfo.getSex(),
//                userInfo.getSignature()});
        db.close();
    }

    @Override
    public void delete(UserInfo userInfo) {
        db = helper.getWritableDatabase();
        db.delete(DBHelper.TBL_NAME_USER,
                "user_name=?",new String[]{userInfo.getUsername()});
        db.close();
    }

    @Override
    public void update(UserInfo userInfo) {
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("user_name",userInfo.getUsername());
        values.put("nick_name",userInfo.getNickname());
        values.put("sex",userInfo.getSex());
        values.put("signature",userInfo.getSignature());
        db.update(DBHelper.TBL_NAME_USER,values,"user_name=?",new String[]{userInfo.getUsername()});

//        String sql = "insert into " + DBHelper.TBL_NAME_USER + " values(null,?,?,?,?)";
//        db.execSQL(sql,new String[]{
//                userInfo.getUsername(),
//                userInfo.getNickname(),
//                userInfo.getSex(),
//                userInfo.getSignature()});
        db.close();
    }
}
