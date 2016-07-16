package com.mystery0.imystery0.DatebaseHelper;

import android.content.Context;
import android.util.Log;

import com.mystery0.imystery0.BaseClass.HeadFile;
import com.mystery0.imystery0.IFindCallback;
import com.mystery0.imystery0.PublicMethod.GetErrorInfo;

import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Created by myste on 2016-7-16-0016.
 * 查询重写
 */
public abstract class FindListenerHelper<T> extends FindListener
{
    private Context context;
    private IFindCallback callback;

    public FindListenerHelper(Context context, IFindCallback callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    public void onSuccess(List list)
    {

    }

    @Override
    public void onError(int i, String s)
    {
        GetErrorInfo.getErrorInfo(context, i, s);
    }
}
