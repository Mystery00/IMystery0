package com.mystery0.imystery0.PublicMethod;

import android.content.Context;
import android.content.SharedPreferences;

import com.mystery0.imystery0.BaseClass.HeadFile;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by myste on 2016-7-13-0013.
 */
public class GetHeadFile
{
    public static String getHeadFile(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        BmobQuery<HeadFile> query = new BmobQuery<>();
        query.addWhereEqualTo("username", sharedPreferences.getString("username", "null"));
        query.findObjects(context, new FindListener<HeadFile>()
        {
            @Override
            public void onSuccess(List<HeadFile> list)
            {
                for (HeadFile headFile : list)
                {
                    BmobFile bmobFile = new BmobFile()
                }
            }

            @Override
            public void onError(int i, String s)
            {

            }
        });
    }
}
