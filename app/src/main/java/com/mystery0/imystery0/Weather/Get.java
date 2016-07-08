package com.mystery0.imystery0.Weather;

import android.util.Log;


import com.mystery0.imystery0.R;

import java.lang.reflect.Field;

/**
 * Created by myste on 2016-6-23-0023.
 * 通过天气代码获取对应天气图片
 */
public class Get
{
    static public int get(String info)
    {
        try
        {
            Field field = R.drawable.class.getField(info);
            return field.getInt(new R.drawable());
        } catch (Exception e)
        {
            Log.e("error", "错误!!!!");
            return R.drawable.tt999;
        }
    }
}
