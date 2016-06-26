package com.mystery0.imystery0.log_in_java;

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

import com.mystery0.imystery0.MainActivity;
import com.mystery0.imystery0.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by myste on 2016-6-2-0002.
 * 登录界面
 */

public class log_in extends Activity implements View.OnClickListener
{
    private boolean isRememberMe=false;
    private EditText user_name;
    private EditText pass_word;
    private ImageView RememberMe;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "7316434f9448bb798f410da5d00b1b1c");
        setContentView(R.layout.log_in);

        initialization();
    }


    void initialization()
    {
        user_name = (EditText) findViewById(R.id.username);
        pass_word = (EditText) findViewById(R.id.password);
        ImageButton login = (ImageButton) findViewById(R.id.Login_button);
        RememberMe = (ImageView) findViewById(R.id.image_point);
        TextView forgot = (TextView) findViewById(R.id.forgot);
        TextView register = (TextView) findViewById(R.id.intent_register);
        isRemember();

        login.setOnClickListener(this);
        RememberMe.setOnClickListener(this);
        register.setOnClickListener(this);
        forgot.setOnClickListener(this);
    }

    void isRemember()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        isRememberMe = sharedPreferences.getBoolean("isRemember", false);
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        Log.i("info", isRememberMe + "记住密码");
        if (isRememberMe)
        {
            user_name.setText(preferences.getString("username", ""));
            pass_word.setText(preferences.getString("password", ""));
            RememberMe.setImageResource(R.drawable.point_1);
        } else
        {
            RememberMe.setImageResource(R.drawable.point_0);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Login_button:
                final ProgressDialog progressDialog=new ProgressDialog(log_in.this);
                progressDialog.setTitle("登录中......");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                String username = user_name.getText().toString();
                String password = pass_word.getText().toString();
                if(isRememberMe)
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();
                }
                final BmobUser user=new BmobUser();
                user.setUsername(username);
                user.setPassword(password);
                user.login(log_in.this, new SaveListener()
                {
                    @Override
                    public void onSuccess()
                    {
                        progressDialog.dismiss();
                        Toast.makeText(log_in.this,"登陆成功!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(log_in.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(log_in.this,"账号或密码错误,请重新登陆!",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.image_point:
                if(isRememberMe)
                {
                    SharedPreferences sharedPreferences=getSharedPreferences("isRememberMe",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("isRemember",false);
                    isRememberMe = true;
                    editor.apply();
                    RememberMe.setImageResource(R.drawable.point_0);
                }
                else
                {
                    SharedPreferences sharedPreferences=getSharedPreferences("isRememberMe",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("isRemember",true);
                    isRememberMe = false;
                    editor.apply();
                    RememberMe.setImageResource(R.drawable.point_1);
                }
                break;
            case R.id.intent_register:
                startActivity(new Intent(log_in.this,Register.class));
                break;
            case R.id.forgot:
                Toast.makeText(log_in.this,"此功能正在开发中!",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
