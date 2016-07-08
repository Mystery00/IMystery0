package com.mystery0.imystery0.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.mystery0.imystery0.BaseClass.City;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-6-22-0022.
 * 市列表适配器
 */
public class CityAdapter extends BaseAdapter
{
    private List<City> cityList;
    private Context context;

    public CityAdapter(List<City> cityList, Context context)
    {
        this.cityList = cityList;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return cityList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).
                inflate(R.layout.item_city, null);
        TextView city_item = (TextView) convertView.findViewById(R.id.item_text);
        City city = cityList.get(position);
        city_item.setText(city.getCity_name());
        return convertView;
    }
}
