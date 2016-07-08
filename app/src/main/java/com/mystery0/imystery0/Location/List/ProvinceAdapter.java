package com.mystery0.imystery0.Location.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.mystery0.imystery0.Class_class.Province;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-6-22-0022.
 * 省适配器
 */
public class ProvinceAdapter extends BaseAdapter
{
    private List<Province> provinceList;
    private Context context;

    public ProvinceAdapter(List<Province> provinceList, Context context)
    {
        this.provinceList = provinceList;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return provinceList.size();
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
        TextView province_item = (TextView) convertView.findViewById(R.id.item_text);
        Province province = provinceList.get(position);
        province_item.setText(province.getProvince_name());
        return convertView;
    }
}
