package com.mystery0.imystery0.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mystery0.imystery0.PublicMethod.GetErrorInfo;
import com.mystery0.imystery0.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by myste on 2016-6-2-0002.
 * 登录界面
 */

public class LoginActivity extends Activity implements View.OnClickListener
{
    private EditText user_name;
    private EditText pass_word;
    private ImageView RememberMe;
    private ImageButton login;
    private TextView register;
    private TextView forgot;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "7316434f9448bb798f410da5d00b1b1c");
        setContentView(R.layout.activity_log_in);

        initView();

        login.setOnClickListener(this);
        RememberMe.setOnClickListener(this);
        register.setOnClickListener(this);
        forgot.setOnClickListener(this);
    }


    void initView()
    {
        user_name = (EditText) findViewById(R.id.username);
        pass_word = (EditText) findViewById(R.id.password);
        login = (ImageButton) findViewById(R.id.Login_button);
        RememberMe = (ImageView) findViewById(R.id.image_point);
        forgot = (TextView) findViewById(R.id.forgot);
        register = (TextView) findViewById(R.id.intent_register);
        isRemember();
    }

    void isRemember()
    {
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        if (isRememberMe())
        {
            user_name.setText(preferences.getString("username", ""));
            pass_word.setText(preferences.getString("password", ""));
            RememberMe.setImageResource(R.drawable.ic_point_1);
            if (isAutoLogin())
            {
                login();
            }
        } else
        {
            RememberMe.setImageResource(R.drawable.ic_point_0);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Login_button:
                saveInfo(user_name.getText().toString(), pass_word.getText().toString());
                login();
                break;
            case R.id.image_point:
                if (isRememberMe())
                {
                    saveSetting(true);
                    RememberMe.setImageResource(R.drawable.ic_point_0);
                } else
                {
                    saveSetting(false);
                    RememberMe.setImageResource(R.drawable.ic_point_1);
                }
                break;
            case R.id.intent_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.forgot:
                Toast.makeText(LoginActivity.this, "此功能正在开发中!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void login()
    {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("登录中......");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        String username = user_name.getText().toString();
        String password = pass_word.getText().toString();
        final BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.login(LoginActivity.this, new SaveListener()
        {
            @Override
            public void onSuccess()
            {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(int i, String s)
            {
                progressDialog.dismiss();
                GetErrorInfo.getErrorInfo(LoginActivity.this, i, s);
            }
        });
    }

    private boolean isAutoLogin()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isAutoLogin", false);
    }

    private boolean isRememberMe()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isRemember", false);
    }

    private void saveInfo(String username, String password)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private void saveSetting(boolean setting)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isRemember", !setting);
        editor.apply();
    }
}
