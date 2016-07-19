package com.mystery0.imystery0.DatebaseHelper;

import android.content.Context;

import com.mystery0.imystery0.BaseClass.HeadFile;
import com.mystery0.imystery0.Callback.IFindCallback;
import com.mystery0.imystery0.PublicMethod.GetErrorInfo;

import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Created by myste on 2016-7-16-0016.
 * 查询重写
 */
public class FindListenerHelper extends FindListener<HeadFile>
{
    private Context context;
    private IFindCallback callback;

    public FindListenerHelper(Context context, IFindCallback callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    public void onSuccess(List<HeadFile> list)
    {
        if (list.size() == 0)
        {
            callback.GetId(null);
        } else
        {
            HeadFile headFile = list.get(0);
            callback.GetId(headFile.getObjectId());
            callback.GetPath(headFile.getHeadFilePath());
        }
    }

    @Override
    public void onError(int i, String s)
    {
        GetErrorInfo.getErrorInfo(context, i, s);
    }
}
