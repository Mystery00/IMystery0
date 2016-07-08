package com.mystery0.imystery0.Location.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.mystery0.imystery0.Class_class.Province;
import com.mystery0.imystery0.Location.GetList;
import com.mystery0.imystery0.R;

import java.util.List;


public class Province_List extends Activity implements AdapterView.OnItemClickListener
{
    private String[] Province_list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province_list);

        ListView province_list = (ListView) findViewById(R.id.Province);

        GetList getList = new GetList();
        List<Province> provinceList = getList.getProvince(this.getApplicationContext());
        ProvinceAdapter adapter = new ProvinceAdapter(provinceList, Province_List.this);
        province_list.setAdapter(adapter);//加载适配器
        Province_list = getList.getCount("Province");//获取数组
        province_list.setOnItemClickListener(this);//条目点击事件
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String province = Province_list[position];
        Intent intent = new Intent(Province_List.this, City_List.class);
        intent.putExtra("province", province);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
            if (resultCode == RESULT_OK)
            {
                Intent intent = new Intent();
                intent.putExtra("code", data.getStringExtra("code"));
                setResult(RESULT_OK, intent);
                finish();
            }
    }
}
