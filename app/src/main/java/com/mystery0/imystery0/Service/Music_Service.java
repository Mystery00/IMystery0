package com.mystery0.imystery0.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by myste on 2016-6-4-0004.
 * 音乐播放服务
 */
public class Music_Service extends Service
{
    static private MediaPlayer mp = new MediaPlayer();

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String path = intent.getStringExtra("music");
        Log.i("info", path);
        try
        {
            mp.setDataSource(path);
            Log.i("info", "准备资源");
            mp.prepare();
            mp.start();
        } catch (IOException e)
        {
            e.printStackTrace();
            mp.reset();
            Log.i("error", "错误!!!!!!!");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mp.stop();
        mp.release();
    }

    public static void Pause()
    {
        mp.pause();
    }

    public static void Play()
    {
        mp.start();
    }
}
