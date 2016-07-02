package com.mystery0.imystery0.Login_Register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mystery0.imystery0.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by myste on 2016-6-2-0002.
 * 注册界面
 */
public class Register extends Activity
{
    private EditText user_name;
    private EditText pass_word;
    private ImageButton Register;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "7316434f9448bb798f410da5d00b1b1c");
        setContentView(R.layout.register);

        initialization();

        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final ProgressDialog progressDialog=new ProgressDialog(Register.this);
                progressDialog.setTitle("注册中......");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                username=user_name.getText().toString();
                password=pass_word.getText().toString();
                final BmobUser user=new BmobUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUp(Register.this, new SaveListener()
                {
                    @Override
                    public void onSuccess()
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this,"注册成功!",Toast.LENGTH_SHORT).show();
                        Log.i("info", "添加数据成功，返回objectId为：" + user.getObjectId());
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this,"注册失败,请检查网络连接",Toast.LENGTH_SHORT).show();
                        Log.i("info","错误原因:"+s);
                    }
                });
            }
        });
    }
    void initialization()
    {
        user_name=(EditText)findViewById(R.id.re_username);
        pass_word=(EditText)findViewById(R.id.re_password);
        Register=(ImageButton)findViewById(R.id.register_button);
    }
}
