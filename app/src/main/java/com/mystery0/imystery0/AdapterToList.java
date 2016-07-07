package com.mystery0.imystery0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myste on 2016-6-26-0026.
 * 菜单列表
 */
public class AdapterToList
{
    public static List<Map<String, Object>> getList()
    {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("pic", R.drawable.music_player);
        map.put("text", "音乐播放器");
        list.add(map);

        map = new HashMap<>();
        map.put("pic", R.drawable.chat_online);
        map.put("text", "与机器人聊天");
        list.add(map);

        map = new HashMap<>();
        map.put("pic", R.drawable.feedback_logo);
        map.put("text", "意见反馈");
        list.add(map);

        map=new HashMap<>();
        map.put("pic",R.drawable.setting);
        map.put("text","个人设置");
        list.add(map);

        return list;
    }
}
