package com.mystery0.imystery0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mystery0.imystery0.Feed_back.Feedback_main;
import com.mystery0.imystery0.Music_player.MusicActivity;


import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by myste on 2016-6-2-0002.
 */
public class MainActivity extends Activity
{
    private ListView listView;
    String[] text={"音乐播放器","意见反馈"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "7316434f9448bb798f410da5d00b1b1c");
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        BmobUpdateAgent.update(this);
        /**
        * 调用更新检测
        */
        BmobInstallation.getCurrentInstallation(this).save();
        BmobPush.startWork(this);



        listView=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,text);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position+1)
                {
                    case 1:
                        startActivity(new Intent(MainActivity.this, MusicActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Feedback_main.class));
                        break;
                }
            }
        });
    }
}
