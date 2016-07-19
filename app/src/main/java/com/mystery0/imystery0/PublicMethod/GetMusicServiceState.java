package com.mystery0.imystery0.PublicMethod;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by myste on 2016-7-19-0019.
 * 获取服务运行状态
 */
public class GetMusicServiceState
{
    public static boolean isServiceRunning(Context context, String serviceName)
    {
        // 校验服务是否还存在
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : services)
        {
            // 得到所有正在运行的服务的名称
            String name = info.service.getClassName();
            if (serviceName.equals(name))
            {
                return true;
            }
        }
        return false;
    }
}
