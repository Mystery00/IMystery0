package com.mystery0.imystery0.splash_viewpager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mystery0.imystery0.log_in_java.log_in;
import com.mystery0.imystery0.R;

/**
 * Created by myste on 2016-6-2-0002.
 */
public class Splash extends Activity
{
    boolean isFirstIn = false;

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final long SPLASH_DELAY_MILLIS = 3000; // 延迟3秒
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        jurgement();
    }
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case GO_GUIDE:
                    SharedPreferences preferences=getSharedPreferences("in",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean("isFirstIn",false);//设置isFirstIn为false
                    editor.apply();
                    startActivity(new Intent(Splash.this,viewpager.class));
                    finish();//销毁Activity
                    break;
                case GO_HOME:
                    startActivity(new Intent(Splash.this,log_in.class));
                    finish();
                    break;
            }
        }
    };
    void jurgement()
    {
        SharedPreferences preferences=getSharedPreferences("in",MODE_PRIVATE);
        isFirstIn=preferences.getBoolean("isFirstIn",true);
        //获取数据库中isFirstIn数据,若没有,则创建,初始值为true
        if(isFirstIn)
        {
            handler.sendEmptyMessageDelayed(GO_GUIDE,SPLASH_DELAY_MILLIS);//设置信息以及延时
        }
        else
        {
            handler.sendEmptyMessageDelayed(GO_HOME,SPLASH_DELAY_MILLIS);
        }

    }
}

