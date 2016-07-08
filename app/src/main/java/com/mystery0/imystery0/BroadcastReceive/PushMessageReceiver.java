package com.mystery0.imystery0.BroadcastReceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mystery0.imystery0.PublicMethod.PushNotification;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;

/**
 * Created by myste on 2016-6-13-0013.
 * 推送接收器
 */
public class PushMessageReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        PushNotification pushNotification = new PushNotification();
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE))
        {
            try
            {
                JSONObject jsonObject=new JSONObject(intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING));
                pushNotification.pushText(jsonObject.getString("alert"), context);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}
