package com.mystery0.imystery0.Push_Service;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mystery0.imystery0.R;

public class pushActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);//1为通知id号
        AlertDialog.Builder builder=new AlertDialog.Builder(this);//创建对话框
        builder.setTitle("来自开发者的消息");//设置标题
        builder.setIcon(R.drawable.logo_3636);//设置图标
        builder.setMessage(Push.getmsg());//设置内容
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener()//设置按钮
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();//对话框消失
                finish();
            }
        });
        builder.create().show();//对话框创建并显示,可异步
    }
}
