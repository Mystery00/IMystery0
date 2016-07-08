package com.mystery0.imystery0.PublicMethod;

/**
 * Created by myste on 2016-7-8-0008.
 * 将歌曲的时间转换为分秒的制度
 */
public class FormatTime
{
    public static String formatTime(Long time)
    {
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
