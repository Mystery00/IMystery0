package com.mystery0.imystery0.Music_player;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.mystery0.imystery0.Class_class.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myste on 2016-6-5-0005.
 * 加载音乐列表
 */
public class Music_List
{
    private String musiclist[]=new String[1024];
    private int k=0;
    public List<Music> getMusic(ContentResolver contentResolver)
    {
        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        assert cursor != null;

        List<Music> musics=new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++)
        {
            cursor.moveToNext();

            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));   //音乐id
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));  //文件路径
            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片
            long album_id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)); //唱片图片ID
            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐

            if (isMusic != 0 && duration/(1000 * 60) >= 1) //只把1分钟以上的音乐添加到集合当中
            {
                musiclist[k]=url;
                k++;
                Music music = new Music();
                music.setId(id);
                music.setTitle(title);
                music.setArtist(artist);
                music.setDuration(duration);
                music.setSize(size);
                music.setUrl(url);
                music.setAlbum(album);
                music.setAlbum_id(album_id);
                musics.add(music);
            }
        }
        cursor.close();
        return musics;
    }
    public String[] getmusiclist()
    {
        return musiclist;
    }
}
