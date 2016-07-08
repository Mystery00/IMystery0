package com.mystery0.imystery0.Location.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.mystery0.imystery0.Class_class.District;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-6-22-0022.
 * 县适配器
 */
public class DistrictAdapter extends BaseAdapter
{
    private List<District> districtList;
    private Context context;

    public DistrictAdapter(List<District> districtList, Context context)
    {
        this.districtList = districtList;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return districtList.size();
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
        TextView district_item = (TextView) convertView.findViewById(R.id.item_text);
        District district = districtList.get(position);
        district_item.setText(district.getDistrict_name());
        return convertView;
    }
}
