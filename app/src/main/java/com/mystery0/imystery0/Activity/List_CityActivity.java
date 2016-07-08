package com.mystery0.imystery0.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.mystery0.imystery0.Adapter.CityAdapter;
import com.mystery0.imystery0.BaseClass.City;
import com.mystery0.imystery0.PublicMethod.GetCityList;
import com.mystery0.imystery0.R;

import java.util.List;

public class List_CityActivity extends Activity implements AdapterView.OnItemClickListener
{
    private String[] City_list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        Intent intent = getIntent();
        String province = intent.getStringExtra("province");

        ListView city_List = (ListView) findViewById(R.id.City);

        GetCityList getCityList = new GetCityList();
        List<City> cityList = getCityList.getCity(this.getApplicationContext(), province);
        CityAdapter adapter = new CityAdapter(cityList, List_CityActivity.this);
        city_List.setAdapter(adapter);//加载适配器
        City_list = getCityList.getCount("City");//获取数组
        city_List.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String city = City_list[position];
        Intent intent = new Intent(List_CityActivity.this, List_DistrictActivity.class);
        intent.putExtra("city", city);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 2)
            if (resultCode == RESULT_OK)
            {
                Intent intent = new Intent();
                intent.putExtra("code", data.getStringExtra("code"));
                setResult(RESULT_OK, intent);
                finish();
            }
    }
}
