package com.mystery0.imystery0.PublicMethod;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mystery0.imystery0.DatebaseHelper.ISQLiteOpenHelper;


/**
 * Created by myste on 2016-6-21-0021.
 * 通过sql数据库查询城市对应的代码
 */
public class FindCityCode
{
    private String id;

    public String Find_Code(Context context, String district)
    {
        ISQLiteOpenHelper isqLiteOpenHelper = new ISQLiteOpenHelper(context, "city_list.db");
        SQLiteDatabase db = isqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("city_list", new String[]{"ID"}, "District=?", new String[]{district}, null, null, null);
        if (cursor.moveToNext())
        {
            id = cursor.getString(cursor.getColumnIndex("ID"));
        }
        cursor.close();
        return id;
    }
}
