package com.mystery0.imystery0.Setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.mystery0.imystery0.MainActivity;
import com.mystery0.imystery0.Setting.AboutUS.About;
import com.mystery0.imystery0.Setting.Feed_back.Feedback_Activity;
import com.mystery0.imystery0.R;
import com.mystery0.imystery0.Setting.ThemeSetting.ThemeSetting;

/**
 * Created by myste on 2016-7-7-0007.
 * 设置界面
 */
public class Setting_Activity extends Activity implements Switch.OnCheckedChangeListener, View.OnClickListener
{
    private RelativeLayout personalLayout;
    private RelativeLayout themeLayout;
    private RelativeLayout feedBackLayout;
    private RelativeLayout aboutUsLayout;
    private Switch autoLogin;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initialization();

        autoLogin.setOnCheckedChangeListener(this);
        back.setOnClickListener(this);
        feedBackLayout.setOnClickListener(this);
        aboutUsLayout.setOnClickListener(this);
        themeLayout.setOnClickListener(this);
    }

    private void initialization()
    {
        personalLayout = (RelativeLayout) findViewById(R.id.setting_head);
        themeLayout = (RelativeLayout) findViewById(R.id.setting_theme);
        feedBackLayout = (RelativeLayout) findViewById(R.id.setting_feedback);
        aboutUsLayout = (RelativeLayout) findViewById(R.id.setting_AboutUS);
        autoLogin = (Switch) findViewById(R.id.switch_autoLogin);
        back = (ImageView) findViewById(R.id.back_setting);

        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        Log.i("info", "" + sharedPreferences.getBoolean("isRemember", false));
        if (sharedPreferences.getBoolean("isAutoLogin", false))
        {
            autoLogin.setChecked(true);
        } else
        {
            autoLogin.setChecked(false);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (b)
        {
            if (!sharedPreferences.getBoolean("isRemember", false))
            {
                Toast.makeText(Setting_Activity.this, "请去登录界面勾选记住密码再使用自动登录功能!", Toast.LENGTH_SHORT).show();
                autoLogin.setChecked(false);
            } else
            {
                autoLogin.setChecked(true);
                editor.putBoolean("isAutoLogin", true);
            }
        } else
        {
            autoLogin.setChecked(false);
            editor.putBoolean("isAutoLogin", false);
        }
        editor.apply();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.back_setting:
                startActivity(new Intent(Setting_Activity.this, MainActivity.class));
                finish();
                break;
            case R.id.setting_feedback:
                startActivity(new Intent(Setting_Activity.this, Feedback_Activity.class));
                break;
            case R.id.setting_AboutUS:
                startActivity(new Intent(Setting_Activity.this, About.class));
                break;
            case R.id.setting_theme:
                startActivity(new Intent(Setting_Activity.this, ThemeSetting.class));
                finish();
                break;
        }
    }
}
