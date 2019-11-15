package com.example.myapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "course.json.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME_USER = "tb_user";
    public static final String TBL_USER = "create table if not exists " +
            TBL_NAME_USER +
            "(_id integer primary key autoincrement, " +
            "user_name varchar, " +
            "nick_name varchar, " +
            "sex varchar, " +
            "signature varchar)";

    public static final String TBL_NAME_HISTORY="tb_history";
    public static final String TBL_HISTORY = "create table if not exists " +
            TBL_NAME_HISTORY +
            "(user_name varchar, " +
            "play_time varchar, " +
            "title varchar, " +
            "video_title varchar)";

    private static DBHelper helper;
    private DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static DBHelper getInstance(Context context) {
        if(helper == null) {
            helper = new DBHelper(context);
        }
        return helper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBL_USER);
        sqLiteDatabase.execSQL(TBL_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TBL_NAME_USER);
        sqLiteDatabase.execSQL("drop table if exists "+TBL_NAME_HISTORY);
        onCreate(sqLiteDatabase);
    }
}