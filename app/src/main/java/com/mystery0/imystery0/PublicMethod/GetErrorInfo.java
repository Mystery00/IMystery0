package com.mystery0.imystery0.PublicMethod;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by myste on 2016-7-16-0016.
 * 显示错误信息
 */
public class GetErrorInfo
{
    public static void getErrorInfo(Context context, int errorCode, String info)
    {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
        Log.e("info", "错误代码:" + errorCode + "\n错误原因:" + info);
    }
}
