package com.mystery0.imystery0.DatebaseHelper;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mystery0.imystery0.ILocationCallback;

/**
 * Created by myste on 2016-6-21-0021.
 * 定位
 */
public class LocationHelper implements AMapLocationListener
{
    private final ILocationCallback callback;
    private AMapLocationClient locationClient;

    public LocationHelper(ILocationCallback callback, Context context)
    {
        this.callback = callback;
        locationClient = new AMapLocationClient(context);
        locationClient.setLocationListener(this);
        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();

        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClientOption.setNeedAddress(true);
        locationClientOption.setInterval(10000);
        locationClient.setLocationOption(locationClientOption);
    }

    public void startLocation()
    {
        locationClient.startLocation();
    }

    public void stopLocation()
    {
        locationClient.stopLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation)
    {
        if (aMapLocation != null)
        {
            if (aMapLocation.getErrorCode() == 0)
            {
                String info = aMapLocation.getDistrict();
                callback.onLocationChanged(info);
            } else
            {
                Log.e("error", "" + aMapLocation.getErrorCode());
                Log.e("error", aMapLocation.getErrorInfo());
                callback.onLocationError(aMapLocation.getErrorInfo());
            }
        }
    }
}
