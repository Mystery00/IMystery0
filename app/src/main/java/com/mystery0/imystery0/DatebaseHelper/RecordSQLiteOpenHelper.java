package com.mystery0.imystery0.DatebaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by myste on 2016-7-1-0001.
 * 数据库
 */
public class RecordSQLiteOpenHelper extends SQLiteOpenHelper
{

    public RecordSQLiteOpenHelper(Context context, String name)
    {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "CREATE TABLE history_list(id integer primary key autoincrement,content text not null,type integer);");
        //建表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
