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
    static private MediaPlayer mp;

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public static void Prepare(String path)
    {
        mp = new MediaPlayer();
        try
        {
            if (mp.isPlaying())
            {
                mp.stop();
                mp.release();
            }
            mp.reset();
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
        } catch (IOException e)
        {
            e.printStackTrace();
            Log.i("error", "错误!!!!!!!");
        }
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
