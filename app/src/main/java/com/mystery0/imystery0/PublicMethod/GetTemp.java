package com.mystery0.imystery0.PublicMethod;

import android.content.SharedPreferences;

/**
 * Created by myste on 2016-6-23-0023.
 * 获取缓存的类
 */
public class GetTemp
{
    private String district;
    private String date;
    private String date_1;
    private String date_2;
    private String date_3;
    private String date_4;
    private String date_5;
    private String date_6;
    private String tmp_0;
    private String tmp_1;
    private String tmp_2;
    private String tmp_3;
    private String tmp_4;
    private String tmp_5;
    private String tmp_6;
    private String img_code;
    private String code_1;
    private String code_2;
    private String code_3;
    private String code_4;
    private String code_5;
    private String code_6;

    public GetTemp(SharedPreferences sharedPreferences)
    {
        district = sharedPreferences.getString("district", "");
        date = sharedPreferences.getString("date", "");
        date_1 = sharedPreferences.getString("date_1", "");
        date_2 = sharedPreferences.getString("date_2", "");
        date_3 = sharedPreferences.getString("date_3", "");
        date_4 = sharedPreferences.getString("date_4", "");
        date_5 = sharedPreferences.getString("date_5", "");
        date_6 = sharedPreferences.getString("date_6", "");
        tmp_0 = sharedPreferences.getString("tmp_0", "");
        tmp_1 = sharedPreferences.getString("tmp_1", "");
        tmp_2 = sharedPreferences.getString("tmp_2", "");
        tmp_3 = sharedPreferences.getString("tmp_3", "");
        tmp_4 = sharedPreferences.getString("tmp_4", "");
        tmp_5 = sharedPreferences.getString("tmp_5", "");
        tmp_6 = sharedPreferences.getString("tmp_6", "");
        img_code = sharedPreferences.getString("img_code", "tt999");
        code_1 = sharedPreferences.getString("code_1", "tt999");
        code_2 = sharedPreferences.getString("code_2", "tt999");
        code_3 = sharedPreferences.getString("code_3", "tt999");
        code_4 = sharedPreferences.getString("code_4", "tt999");
        code_5 = sharedPreferences.getString("code_5", "tt999");
        code_6 = sharedPreferences.getString("code_6", "tt999");
    }

    public String getDistrict()
    {
        return district;
    }

    public String getDate_0()
    {
        return date;
    }

    public String getDate_1()
    {
        return date_1;
    }

    public String getDate_2()
    {
        return date_2;
    }

    public String getDate_3()
    {
        return date_3;
    }

    public String getDate_4()
    {
        return date_4;
    }

    public String getDate_5()
    {
        return date_5;
    }

    public String getDate_6()
    {
        return date_6;
    }

    public String getTmp_0()
    {
        return tmp_0;
    }

    public String getTmp_1()
    {
        return tmp_1;
    }

    public String getTmp_2()
    {
        return tmp_2;
    }

    public String getTmp_3()
    {
        return tmp_3;
    }

    public String getTmp_4()
    {
        return tmp_4;
    }

    public String getTmp_5()
    {
        return tmp_5;
    }

    public String getTmp_6()
    {
        return tmp_6;
    }

    public String getCode_0()
    {
        return img_code;
    }

    public String getCode_1()
    {
        return code_1;
    }

    public String getCode_2()
    {
        return code_2;
    }

    public String getCode_3()
    {
        return code_3;
    }

    public String getCode_4()
    {
        return code_4;
    }

    public String getCode_5()
    {
        return code_5;
    }

    public String getCode_6()
    {
        return code_6;
    }
}
