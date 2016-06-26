package com.mystery0.imystery0.Push_Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.mystery0.imystery0.R;


/**
 * Created by myste on 2016-6-13-0013.
 * 推送
 */
public class Push
{
    static private String message;
    public void pushtext(String text,Context context)
    {
        message=text;
        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //获取系统通知服务
        Notification.Builder builder=new Notification.Builder(context);

        builder.setSmallIcon(R.drawable.logo_3636);//设置图标
        builder.setTicker("您收到一条新消息");//手机状态栏的提示
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("来自开发者的信息");//设置标题
        builder.setContentText(text);//设置通知内容

        Intent intent = new Intent(context,pushActivity.class);//设疑点击意图
        PendingIntent pintent = PendingIntent.getActivity(context,0, intent,0 );
        builder.setContentIntent(pintent);//点击后的意图
        Notification notification= builder.build();//加载布局

        manager.notify(1,notification);//发出通知,其中1为通知消息id
    }

    static String getmsg()
    {
        return message;
    }
}
