package com.mystery0.imystery0.Location.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.mystery0.imystery0.Class_class.District;
import com.mystery0.imystery0.Location.GetList;
import com.mystery0.imystery0.R;

import java.util.List;

public class District_List extends Activity implements AdapterView.OnItemClickListener
{
    private String[] District_list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_list);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");

        ListView district_list = (ListView) findViewById(R.id.District);

        GetList getList = new GetList();
        List<District> districtList = getList.getDistrict(this.getApplicationContext(), city);
        DistrictAdapter adapter = new DistrictAdapter(districtList, District_List.this);
        district_list.setAdapter(adapter);//加载适配器
        District_list = getList.getCount("District");//获取数组
        district_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String district = District_list[position];
        Intent intent = new Intent();
        intent.putExtra("code", district);
        setResult(RESULT_OK, intent);
        finish();
    }
}
