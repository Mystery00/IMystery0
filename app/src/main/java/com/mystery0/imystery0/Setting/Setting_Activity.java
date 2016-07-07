package com.mystery0.imystery0.Setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.mystery0.imystery0.R;

/**
 * Created by myste on 2016-7-7-0007.
 * 设置界面
 */
public class Setting_Activity extends Activity
{
    private RelativeLayout personalLayout;
    private RelativeLayout themeLayout;
    private RelativeLayout feedBackLayout;
    private RelativeLayout aboutUsLayout;
    private Switch autoLogin;
    private boolean isRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initialization();
    }

    private void initialization()
    {
        personalLayout = (RelativeLayout) findViewById(R.id.setting_head);
        themeLayout = (RelativeLayout) findViewById(R.id.setting_theme);
        feedBackLayout = (RelativeLayout) findViewById(R.id.setting_feedback);
        aboutUsLayout = (RelativeLayout) findViewById(R.id.setting_AboutUS);
        autoLogin = (Switch) findViewById(R.id.switch_autoLogin);

        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        isRememberMe = sharedPreferences.getBoolean("isRemember", false);
    }
}
