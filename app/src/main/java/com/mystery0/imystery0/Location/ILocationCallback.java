package com.mystery0.imystery0.Location;

/**
 * Created by myste on 2016-6-25-0025.
 * 回调
 */
public interface ILocationCallback
{
    void onLocationChanged(String info);

    void onLocationError(String info);
}
