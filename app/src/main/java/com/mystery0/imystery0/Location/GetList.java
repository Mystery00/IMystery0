package com.mystery0.imystery0.Location;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mystery0.imystery0.Class_class.City;
import com.mystery0.imystery0.Class_class.District;
import com.mystery0.imystery0.Class_class.Province;
import com.mystery0.imystery0.Location.SQL.ISQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myste on 2016-6-25-0025.
 * 获取列表
 */
public class GetList
{
    private String[] provincelist = new String[34];
    private String[] citylist = new String[512];
    private String[] districtlist = new String[512];

    public List<Province> getprovince(Context context)
    {
        int i = 0;
        ArrayList<Province> provinces = new ArrayList<>();
        ISQLiteOpenHelper isqLiteOpenHelper = new ISQLiteOpenHelper(context, "city_list.db");
        SQLiteDatabase db = isqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "city_list", new String[]{"Province"}, null, null, null, null, null, null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                Province province = new Province();
                province.setProvince_name(cursor.getString(cursor.getColumnIndex("Province")));
                provinces.add(province);
                provincelist[i] = province.getProvince_name();
                i++;
            }
            Log.i("info", "省数量:" + i);
            cursor.close();
        } else
            Log.e("error", "空指针!!!!");
        db.close();
        return provinces;
    }

    public List<City> getcity(Context context, String mprovince)
    {
        int i = 0;
        ArrayList<City> cities = new ArrayList<>();
        ISQLiteOpenHelper isqLiteOpenHelper = new ISQLiteOpenHelper(context, "city_list.db");
        SQLiteDatabase db = isqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "city_list", new String[]{"City"}, "Province=?", new String[]{mprovince}, null, null, null, null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                City city = new City();
                city.setCity_name(cursor.getString(cursor.getColumnIndex("City")));
                cities.add(city);
                citylist[i] = city.getCity_name();
                i++;
            }
            Log.i("info", "市数量:" + i);
            cursor.close();
        } else
            Log.e("error", "空指针!!!!");
        db.close();
        return cities;
    }

    public List<District> getdistrict(Context context, String mcity)
    {
        int i = 0;
        ArrayList<District> districts = new ArrayList<>();
        ISQLiteOpenHelper isqLiteOpenHelper = new ISQLiteOpenHelper(context, "city_list.db");
        SQLiteDatabase db = isqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "city_list", new String[]{"District"}, "City=?", new String[]{mcity}, null, null, null, null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                District district = new District();
                district.setDistrict_name(cursor.getString(cursor.getColumnIndex("District")));
                districts.add(district);
                districtlist[i] = district.getDistrict_name();
                i++;
            }
            Log.i("info", "县数量:" + i);
            cursor.close();
        } else
            Log.e("error", "空指针!!!!");
        db.close();
        return districts;
    }

    public String getid(Context context, String mdistrict)
    {
        String ID = null;
        ArrayList<District> districts = new ArrayList<>();
        ISQLiteOpenHelper isqLiteOpenHelper = new ISQLiteOpenHelper(context, "city_list.db");
        SQLiteDatabase db = isqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "city_list", new String[]{"ID"}, "District=?", new String[]{mdistrict}, null, null, null, null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                ID = cursor.getString(cursor.getColumnIndex("ID"));
            }
            cursor.close();
        } else
            Log.e("error", "空指针!!!!");
        db.close();
        return ID;
    }

    public String[] getcount(String get)
    {
        switch (get)
        {
            case "Province":
                return provincelist;
            case "City":
                return citylist;
            case "District":
                return districtlist;
            default:
                Log.e("error", "获取信息错误!!!!!!!!!!");
                return null;
        }
    }
}
