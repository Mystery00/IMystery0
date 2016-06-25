package com.mystery0.imystery0.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by myste on 2016-6-25-0025.
 */
public class Creat_Table
{
    public void creat(Context context)
    {
        ISQLiteOpenHelper isqLiteOpenHelper = new ISQLiteOpenHelper(context, "city_list.db");
        SQLiteDatabase sqLiteDatabase = isqLiteOpenHelper.getWritableDatabase();
    }
}
