package com.mystery0.imystery0.PublicMethod;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mystery0.imystery0.BaseClass.HeadFile;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
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
    public static void getHeadFile(final Context context)
    {
        Bmob.initialize(context, "7316434f9448bb798f410da5d00b1b1c");
        final SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        BmobQuery<HeadFile> query = new BmobQuery<>();
        query.addWhereEqualTo("username", sharedPreferences.getString("username", "null"));
        query.findObjects(context, new FindListener<HeadFile>()
        {
            @Override
            public void onSuccess(List<HeadFile> list)
            {
                Log.i("info", "成功找到文件路径!");
                for (HeadFile headFile : list)
                {
                    BmobFile bmobFile = new BmobFile(sharedPreferences.getString("username", "null"), "", headFile.getHeadFilePath());
                    bmobFile.download(context, new DownloadFileListener()
                    {
                        @Override
                        public void onSuccess(String s)
                        {
                            Log.i("info", "头像文件下载完成");
                        }

                        @Override
                        public void onFailure(int i, String s)
                        {
                            Log.e("error", "错误代码:" + i);
                            Log.e("error", "错误信息:" + s);
                        }
                    });
                }
            }

            @Override
            public void onError(int i, String s)
            {
                Log.e("error", "错误代码:" + i);
                Log.e("error", "错误信息:" + s);
            }
        });
    }
}
