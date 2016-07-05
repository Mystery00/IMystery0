package com.mystery0.imystery0.Music_Player;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mystery0.imystery0.Class_class.Music;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-6-5-0005.
 * 音乐列表适配器
 */
class ViewContainer
{
    public TextView music_title;
    public TextView music_artist;
    public TextView music_duration;
    public ImageButton list_down_button;
}

public class MusicAdapter extends BaseAdapter
{
    private Context context;        //上下文对象引用
    private List<Music> Musics;   //存放Mp3Info引用的集合
    private ViewContainer vc;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            vc = new ViewContainer();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_music, null);
            vc.music_title = (TextView) convertView.findViewById(R.id.music_title);
            vc.music_artist = (TextView) convertView.findViewById(R.id.music_Artist);
            vc.music_duration = (TextView) convertView.findViewById(R.id.music_duration);
            vc.list_down_button = (ImageButton) convertView.findViewById(R.id.list_down_button);
            convertView.setTag(vc);
        } else
        {
            vc = (ViewContainer) convertView.getTag();
        }
        Music music = Musics.get(position);
        vc.music_title.setText(music.getTitle());         //显示标题
        vc.music_artist.setText(music.getArtist());       //显示艺术家
        vc.music_duration.setText(String.valueOf(formatTime(music.getDuration()))); //显示长度
        return convertView;
    }

    public static String formatTime(Long time)
    {                     //将歌曲的时间转换为分秒的制度
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";

        if (min.length() < 2)
            min = "0" + min;
        switch (sec.length())
        {
            case 4:
                sec = "0" + sec;
                break;
            case 3:
                sec = "00" + sec;
                break;
            case 2:
                sec = "000" + sec;
                break;
            case 1:
                sec = "0000" + sec;
                break;
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
}
