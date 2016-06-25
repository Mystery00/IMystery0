package com.mystery0.imystery0.Music_player;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mystery0.imystery0.Class_class.Music;
import com.mystery0.imystery0.MainActivity;
import com.mystery0.imystery0.R;

import java.util.List;


/**
 * Created by myste on 2016-6-4-0004.
 */
public class MusicActivity extends Activity implements View.OnClickListener
{
    private ImageButton back_button;
    private ListView Music_list;
    private ImageButton last_button;
    private ImageButton play_button;
    private ImageButton next_button;
    private Intent intent;
    private boolean cc=false;
    private Music_List music_list;
    private String path;
    private String[] musiclist;
    private List<Music> Musics;
    private Music_Adapter mAdapter;
    private int nowPlaying=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_layout);

        initialization();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ProgressDialog progressDialog=new ProgressDialog(MusicActivity.this);
        progressDialog.setTitle("正在查找本地音乐...");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        Musics=music_list.getMusic(MusicActivity.this.getContentResolver());
        musiclist=music_list.getmusiclist();
        mAdapter = new Music_Adapter(MusicActivity.this, Musics);
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
                nowPlaying=position;
                prepare_music(nowPlaying);
                play_button.setBackgroundResource(R.drawable.pause_button);
                cc=false;
            }
        });
    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.play_button:
                if(cc)
                {
                    play_button.setBackgroundResource(R.drawable.pause_button);
                    cc = false;
                    MusicPlayerService.Play();
                }
                else
                {
                    play_button.setBackgroundResource(R.drawable.play_button);
                    cc = true;
                    MusicPlayerService.Pause();
                }
                break;
            case R.id.back_Button:
                startActivity(new Intent(MusicActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.last_button:
                nowPlaying--;
                prepare_music(nowPlaying);
                play_button.setBackgroundResource(R.drawable.pause_button);
                cc=false;
                break;
            case R.id.next_button:
                nowPlaying++;
                prepare_music(nowPlaying);
                play_button.setBackgroundResource(R.drawable.pause_button);
                cc=false;
                break;
        }
    }

    void initialization()
    {
        back_button=(ImageButton)findViewById(R.id.back_Button);
        Music_list=(ListView)findViewById(R.id.music_list);
        last_button=(ImageButton)findViewById(R.id.last_button);
        play_button=(ImageButton)findViewById(R.id.play_button);
        next_button=(ImageButton)findViewById(R.id.next_button);
        music_list=new Music_List();
        intent=new Intent(MusicActivity.this,MusicPlayerService.class);
    }

    void prepare_music(int position)
    {
        if(!cc)
            stopService(intent);
        path=musiclist[position];
        MusicPlayerService.Prepare(path);
        startService(intent);
    }
}
