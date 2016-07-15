package com.mystery0.imystery0.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.mystery0.imystery0.BaseClass.CircleImageView;
import com.mystery0.imystery0.DatebaseHelper.SaveListenerHelper;
import com.mystery0.imystery0.ISaveCallback;
import com.mystery0.imystery0.PublicMethod.GetHeadFile;
import com.mystery0.imystery0.PublicMethod.GetPath;
import com.mystery0.imystery0.BaseClass.HeadFile;
import com.mystery0.imystery0.R;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by myste on 2016-7-7-0007.
 * 设置界面
 */
public class SettingActivity extends Activity implements Switch.OnCheckedChangeListener, View.OnClickListener, ISaveCallback
{
    private RelativeLayout personalLayout;
    private RelativeLayout themeLayout;
    private RelativeLayout feedBackLayout;
    private RelativeLayout aboutUsLayout;
    private Switch autoLogin;
    private ImageView back;
    private CircleImageView head;
    private ProgressDialog progressDialog;

    final static public int FILE_SELECT_CODE = 0;
    final static private int SAVE_DONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initialization();

        autoLogin.setOnCheckedChangeListener(this);
        back.setOnClickListener(this);
        personalLayout.setOnClickListener(this);
        feedBackLayout.setOnClickListener(this);
        aboutUsLayout.setOnClickListener(this);
        themeLayout.setOnClickListener(this);
    }

    private void initialization()
    {
        Bmob.initialize(this, "7316434f9448bb798f410da5d00b1b1c");

        personalLayout = (RelativeLayout) findViewById(R.id.setting_head);
        themeLayout = (RelativeLayout) findViewById(R.id.setting_theme);
        feedBackLayout = (RelativeLayout) findViewById(R.id.setting_feedback);
        aboutUsLayout = (RelativeLayout) findViewById(R.id.setting_AboutUS);
        autoLogin = (Switch) findViewById(R.id.switch_autoLogin);
        back = (ImageView) findViewById(R.id.back_setting);
        head = (CircleImageView) findViewById(R.id.img_head_setting);

        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isAutoLogin", false))
        {
            autoLogin.setChecked(true);
        } else
        {
            autoLogin.setChecked(false);
        }

        SetHeadFile();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberMe", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (b)
        {
            if (!sharedPreferences.getBoolean("isRemember", false))
            {
                Toast.makeText(SettingActivity.this, "请去登录界面勾选记住密码再使用自动登录功能!", Toast.LENGTH_SHORT).show();
                autoLogin.setChecked(false);
            } else
            {
                autoLogin.setChecked(true);
                editor.putBoolean("isAutoLogin", true);
            }
        } else
        {
            autoLogin.setChecked(false);
            editor.putBoolean("isAutoLogin", false);
        }
        editor.apply();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.back_setting:
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.setting_feedback:
                startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
                break;
            case R.id.setting_AboutUS:
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                break;
            case R.id.setting_theme:
                startActivity(new Intent(SettingActivity.this, ThemeSettingActivity.class));
                break;
            case R.id.setting_head:
                getHeadFile();
                break;
        }
    }

    /**
     * 调用文件选择软件来选择文件
     **/
    private void getHeadFile()
    {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setType("image/*");
        intent.putExtra("outputX", 100);  //裁剪图片的宽
        intent.putExtra("outputY", 100);
        intent.putExtra("scale", true);  //是否保持比例
        intent.putExtra("return-data", false);  //是否返回bitmap
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());  //输出格式
        startActivityForResult(intent, FILE_SELECT_CODE);
    }

    /**
     * 根据返回选择的文件，来进行上传操作
     **/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            progressDialog = new ProgressDialog(SettingActivity.this);
            progressDialog.setTitle("正在上传头像...");
            progressDialog.setCancelable(false);
            progressDialog.setMessage("已上传0%");
            progressDialog.show();
            String path = GetPath.getPath(this, data.getData());
            final BmobFile bmobFile;
            if (path != null)
            {
                bmobFile = new BmobFile(new File(path));
                bmobFile.uploadblock(this, new UploadFileListener()
                {
                    @Override
                    public void onSuccess()
                    {
                        SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                        HeadFile headFile = new HeadFile();
                        headFile.setUsername(sharedPreferences.getString("username", "null"));
                        headFile.setHeadFileName(sharedPreferences.getString("username", "null") + ".jpg");
                        headFile.setHeadFilePath(bmobFile.getFileUrl(SettingActivity.this));
                        headFile.save(SettingActivity.this, new SaveListenerHelper(SettingActivity.this.getApplicationContext(), progressDialog, SettingActivity.this));
                    }

                    @Override
                    public void onFailure(int i, String s)
                    {
                        Log.e("error", "错误代码:" + i);
                        Log.e("error", "错误信息:" + s);
                        Toast.makeText(SettingActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(Integer value)
                    {
                        progressDialog.setMessage("已上传" + value + "%");
                    }
                });
            }
        }
    }

    @Override
    public void GetSaveState(boolean state)
    {
        if (state)
        {
            Log.i("info", "头像上传成功!");
            Message message = new Message();
            message.what = SAVE_DONE;
            handler.sendMessage(message);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case SAVE_DONE:
                    SetHeadFile();
                    break;
            }
        }
    };

    /**
     * 设置头像文件
     */
    private void SetHeadFile()
    {
        GetHeadFile.getHeadFile(this.getApplicationContext());
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        if (BitmapFactory.decodeFile(getCacheDir() + "/bmob/" + sharedPreferences.getString("username", "null") + ".jpg") != null)
        {
            Log.i("info", "头像更改成功!");
            head.setImageBitmap(BitmapFactory.decodeFile(getCacheDir() + "/bmob/" + sharedPreferences.getString("username", "null") + ".jpg"));
        } else
        {
            Log.i("info", "找不到文件!");
            head.setImageResource(R.drawable.guest);
        }
    }
}