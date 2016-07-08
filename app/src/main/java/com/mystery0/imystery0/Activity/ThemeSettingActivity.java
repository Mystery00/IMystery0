package com.mystery0.imystery0.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mystery0.imystery0.PublicMethod.GetPath;
import com.mystery0.imystery0.R;

/**
 * Created by myste on 2016-7-8-0008.
 * 主题设置
 */
public class ThemeSettingActivity extends Activity implements View.OnClickListener
{
    private ImageView back;
    private TextView text_weather;
    private TextView text_music;
    private TextView text_chat;
    private TextView text_menu;
    private TextView text_save;
    final static public int REQUEST_CODE_RESIZE_IMAGE_WEATHER = 2;
    final static public int REQUEST_CODE_RESIZE_IMAGE_MUSIC = 3;
    final static public int REQUEST_CODE_RESIZE_IMAGE_CHAT = 4;
    final static public int REQUEST_CODE_RESIZE_IMAGE_MENU = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_theme);

        initialization();

        back.setOnClickListener(this);
        text_chat.setOnClickListener(this);
        text_music.setOnClickListener(this);
        text_weather.setOnClickListener(this);
        text_menu.setOnClickListener(this);
        text_save.setOnClickListener(this);
    }

    private void initialization()
    {
        back = (ImageView) findViewById(R.id.back_theme);
        text_chat = (TextView) findViewById(R.id.text_setting_theme_chat);
        text_weather = (TextView) findViewById(R.id.text_setting_theme_weather);
        text_menu = (TextView) findViewById(R.id.text_setting_theme_menu);
        text_music = (TextView) findViewById(R.id.text_setting_theme_music);
        text_save = (TextView) findViewById(R.id.text_setting_theme_save);
    }

    @Override
    public void onClick(View view)
    {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        switch (view.getId())
        {
            case R.id.back_theme:
                startActivity(new Intent(ThemeSettingActivity.this, SettingActivity.class));
                finish();
                break;
            case R.id.text_setting_theme_chat:
                setIntent(width * 160 / densityDpi, height * 160 / densityDpi, REQUEST_CODE_RESIZE_IMAGE_CHAT);
                break;
            case R.id.text_setting_theme_weather:
                setIntent(width * 160 / densityDpi, height * 160 / densityDpi, REQUEST_CODE_RESIZE_IMAGE_WEATHER);
                break;
            case R.id.text_setting_theme_menu:
                setIntent(260, height * 160 / densityDpi, REQUEST_CODE_RESIZE_IMAGE_MENU);
                break;
            case R.id.text_setting_theme_music:
                setIntent(width * 160 / densityDpi, height * 160 / densityDpi, REQUEST_CODE_RESIZE_IMAGE_MUSIC);
                break;
            case R.id.text_setting_theme_save:
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
    }

    private void setIntent(int X, int Y, int type)
    {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setType("image/*");
        intent.putExtra("outputX", X);  //裁剪图片的宽
        intent.putExtra("outputY", Y);
        intent.putExtra("scale", true);  //是否保持比例
        intent.putExtra("return-data", false);  //是否返回bitmap
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());  //输出格式
        startActivityForResult(intent, type);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            String path = GetPath.getPath(this.getApplicationContext(), data.getData());
            SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (requestCode)
            {
                case REQUEST_CODE_RESIZE_IMAGE_CHAT:
                    editor.putString("image_chat", path);
                    break;
                case REQUEST_CODE_RESIZE_IMAGE_WEATHER:
                    editor.putString("image_weather", path);
                    break;
                case REQUEST_CODE_RESIZE_IMAGE_MUSIC:
                    editor.putString("image_music", path);
                    break;
                case REQUEST_CODE_RESIZE_IMAGE_MENU:
                    editor.putString("image_menu", path);
                    break;
            }
            editor.apply();
        }
    }
}
