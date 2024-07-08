package com.example.module_me.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    //定义创建用户数据表的SQL语句  主键user数据库表 username和password字段
    final String CREATE_USER_SQL=
            "create table user(_id integer primary " + "key autoincrement , username, password)";
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,null,version);
    }
    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db){
        //创建用户列表 execSQL执行修改数据库内容的SQL语句
        db.execSQL(CREATE_USER_SQL);
    }

    @Override
    //版本号发生改变时使用
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //提示版本更新
        System.out.println("---版本更新----"+oldVersion+"--->"+newVersion);
    }
}

