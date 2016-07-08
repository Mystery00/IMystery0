package com.mystery0.imystery0.Activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.mystery0.imystery0.BaseClass.Music;
import com.mystery0.imystery0.Adapter.MusicAdapter;
import com.mystery0.imystery0.ContentProvider.MusicListProvider;
import com.mystery0.imystery0.R;
import com.mystery0.imystery0.Service.Music_Service;

import java.io.File;
import java.util.List;


/**
 * Created by myste on 2016-6-4-0004.
 * 播放器视图
 */
public class MusicActivity extends Activity implements View.OnClickListener
{
    private ImageButton back_button;
    private ListView Music_list;
    private ImageButton last_button;
    private ImageButton play_button;
    private ImageButton next_button;
    private Intent intent;
    private boolean cc = false;
    private MusicListProvider music_listProvider;
    private String[] musicList;
    private int nowPlaying = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_layout);

        initialization();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ProgressDialog progressDialog = new ProgressDialog(MusicActivity.this);
        progressDialog.setTitle("正在查找本地音乐...");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        List<Music> musics = music_listProvider.getMusic(MusicActivity.this.getContentResolver());
        musicList = music_listProvider.getMusicList();
        MusicAdapter mAdapter = new MusicAdapter(MusicActivity.this, musics);
        Music_list.setAdapter(mAdapter);
        progressDialog.dismiss();

        play_button.setOnClickListener(this);
        back_button.setOnClickListener(this);
        last_button.setOnClickListener(this);
        next_button.setOnClickListener(this);

        Music_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                nowPlaying = position;
                prepare_music(nowPlaying);
                play_button.setBackgroundResource(R.drawable.btn_pause);
                cc = false;
            }
        });
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.play_button:
                if (cc)
                {
                    play_button.setBackgroundResource(R.drawable.btn_pause);
                    cc = false;
                    Music_Service.Play();
                } else
                {
                    play_button.setBackgroundResource(R.drawable.btn_play);
                    cc = true;
                    Music_Service.Pause();
                }
                break;
            case R.id.back_Button:
                startActivity(new Intent(MusicActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.last_button:
                nowPlaying--;
                prepare_music(nowPlaying);
                play_button.setBackgroundResource(R.drawable.btn_pause);
                cc = false;
                break;
            case R.id.next_button:
                nowPlaying++;
                prepare_music(nowPlaying);
                play_button.setBackgroundResource(R.drawable.btn_pause);
                cc = false;
                break;
        }
    }

    void initialization()
    {
        back_button = (ImageButton) findViewById(R.id.back_Button);
        Music_list = (ListView) findViewById(R.id.music_list);
        last_button = (ImageButton) findViewById(R.id.last_button);
        play_button = (ImageButton) findViewById(R.id.play_button);
        next_button = (ImageButton) findViewById(R.id.next_button);
        music_listProvider = new MusicListProvider();
        intent = new Intent(MusicActivity.this, Music_Service.class);

        SharedPreferences preferences = getSharedPreferences("setting", MODE_PRIVATE);
        if (preferences.getString("image_music", "null") != "null")
        {
            ImageView background = (ImageView) findViewById(R.id.music_background);
            File file = new File(preferences.getString("image_music", ""));
            if (file.exists())
            {
                Bitmap bm = BitmapFactory.decodeFile(preferences.getString("image_music", ""));
                background.setImageBitmap(bm);
            }
        }
    }

    void prepare_music(int position)
    {
        if (!cc)
            stopService(intent);
        String path = musicList[position];
        Music_Service.Prepare(path);
        startService(intent);
    }
}
