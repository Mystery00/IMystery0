package com.mystery0.imystery0.PublicMethod;

import android.content.Context;
import android.util.Log;

import com.mystery0.imystery0.BaseClass.HeadFile;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by myste on 2016-7-16-0016.
 * 查询id
 */
public class QueryHeadFile
{
    public static String queryId(final Context context, String username)
    {
        final String[] id = new String[1];
        Bmob.initialize(context, "7316434f9448bb798f410da5d00b1b1c");
        BmobQuery<HeadFile> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.addQueryKeys("objectId");
        query.findObjects(context, new FindListener<HeadFile>()
        {
            @Override
            public void onSuccess(List<HeadFile> list)
            {
                if (list == null)
                {
                    id[0] = null;
                } else
                {
                    for (HeadFile headFile : list)
                    {
                        Log.i("info", headFile.getObjectId());
                        id[0] = headFile.getObjectId();
                    }
                }
            }

            @Override
            public void onError(int i, String s)
            {
                GetErrorInfo.getErrorInfo(context, i, s);
            }
        });
        return id[0];
    }

    public static String queryPath(final Context context, String username)
    {
        final String[] path = new String[1];
        Bmob.initialize(context, "7316434f9448bb798f410da5d00b1b1c");
        BmobQuery<HeadFile> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.addQueryKeys("headFilePath");
        query.findObjects(context, new FindListener<HeadFile>()
        {
            @Override
            public void onSuccess(List<HeadFile> list)
            {
                for (HeadFile headFile : list)
                {
                    path[0] = headFile.getHeadFilePath();
                }
            }

            @Override
            public void onError(int i, String s)
            {
                GetErrorInfo.getErrorInfo(context, i, s);
            }
        });
        return path[0];
    }
}
