package com.mystery0.imystery0.DatebaseHelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mystery0.imystery0.Callback.ISaveCallback;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by myste on 2016-7-14-0014.
 * 头像文件上传
 */
public class SaveListenerHelper extends SaveListener
{
    private ISaveCallback saveCallback;
    private Context context;
    private ProgressDialog progressDialog;

    public SaveListenerHelper(Context context, ProgressDialog progressDialog, ISaveCallback saveCallback)
    {
        this.saveCallback = saveCallback;
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    public void onSuccess()
    {
        progressDialog.dismiss();
        Toast.makeText(context, "头像设置成功!", Toast.LENGTH_SHORT).show();
        saveCallback.GetSaveState(true);
    }

    @Override
    public void onFailure(int i, String s)
    {
        progressDialog.dismiss();
        Log.e("info", "错误代码:" + i + "错误原因:" + s);
        saveCallback.GetSaveState(false);
    }
}
