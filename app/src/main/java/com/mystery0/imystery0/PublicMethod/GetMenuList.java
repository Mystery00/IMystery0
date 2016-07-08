package com.mystery0.imystery0.PublicMethod;

import com.mystery0.imystery0.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myste on 2016-6-26-0026.
 * 菜单列表
 */
public class GetMenuList
{
    public static List<Map<String, Object>> getList()
    {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("pic", R.drawable.ic_music_player);
        map.put("text", "音乐播放器");
        list.add(map);

        map = new HashMap<>();
        map.put("pic", R.drawable.ic_chat);
        map.put("text", "与机器人聊天");
        list.add(map);

        map=new HashMap<>();
        map.put("pic", R.drawable.ic_setting);
        map.put("text","个人设置");
        list.add(map);

        return list;
    }
}
