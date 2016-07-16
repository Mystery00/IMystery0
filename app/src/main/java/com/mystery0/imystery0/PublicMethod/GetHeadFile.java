package com.mystery0.imystery0.PublicMethod;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.mystery0.imystery0.BaseClass.CircleImageView;
import com.mystery0.imystery0.BaseClass.HeadFile;
import com.mystery0.imystery0.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by myste on 2016-7-13-0013.
 * 下载头像文件
 */
public class GetHeadFile
{
    public static void getHeadFile(final Context context, final CircleImageView head)
    {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        BmobQuery<HeadFile> query = new BmobQuery<>();
        query.addWhereEqualTo("username", sharedPreferences.getString("username", "null"));
        query.findObjects(context, new FindListener<HeadFile>()
        {
            @Override
            public void onSuccess(List<HeadFile> list)
            {
                HeadFile headFile = list.get(0);
                BmobFile bmobFile = new BmobFile(headFile.getHeadFileName(), "", headFile.getHeadFilePath());
                bmobFile.download(context, new DownloadFileListener()
                {
                    @Override
                    public void onSuccess(String s)
                    {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                        if (BitmapFactory.decodeFile(context.getCacheDir() + "/bmob/" + sharedPreferences.getString("username", "null") + ".jpg") != null)
                        {
                            head.setImageBitmap(BitmapFactory.decodeFile(context.getCacheDir() + "/bmob/" + sharedPreferences.getString("username", "null") + ".jpg"));
                        } else
                        {
                            head.setImageResource(R.drawable.guest);
                        }
                    }

                    @Override
                    public void onFailure(int i, String s)
                    {
                        Log.e("info", "错误代码:" + i + "错误原因:" + s);
                    }
                });


            }

            @Override
            public void onError(int i, String s)
            {
                Log.e("info", "错误代码:" + i + "错误原因:" + s);
            }
        });
    }
}
