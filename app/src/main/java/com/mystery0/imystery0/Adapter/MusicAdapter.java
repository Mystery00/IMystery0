package com.mystery0.imystery0.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mystery0.imystery0.BaseClass.Music;
import com.mystery0.imystery0.PublicMethod.FormatTime;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-6-5-0005.
 * 音乐列表适配器
 */

public class MusicAdapter extends BaseAdapter
{
    private Context context;
    private List<Music> Musics;
    private TextView music_title;
    private TextView music_artist;
    private TextView music_duration;

    public MusicAdapter(Context context, List<Music> musics)
    {
        this.context = context;
        this.Musics = musics;
    }

    @Override
    public int getCount()
    {
        return Musics.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music, null);
            music_title = (TextView) convertView.findViewById(R.id.music_title);
            music_artist = (TextView) convertView.findViewById(R.id.music_Artist);
            music_duration = (TextView) convertView.findViewById(R.id.music_duration);
        }
        Music music = Musics.get(position);
        music_title.setText(music.getTitle());         //显示标题
        music_artist.setText(music.getArtist());       //显示艺术家
        music_duration.setText(String.valueOf(FormatTime.formatTime(music.getDuration()))); //显示长度
        return convertView;
    }
}
