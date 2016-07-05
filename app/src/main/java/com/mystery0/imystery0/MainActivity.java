package com.mystery0.imystery0;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mystery0.imystery0.Chat_Online.chat_Activity;
import com.mystery0.imystery0.Feed_back.Feedback_Activity;
import com.mystery0.imystery0.Location.ILocationCallback;
import com.mystery0.imystery0.Location.List.Province_List;
import com.mystery0.imystery0.Location.LocationHelper;
import com.mystery0.imystery0.Music_Player.Music_Activity;
import com.mystery0.imystery0.Weather.FindCode;
import com.mystery0.imystery0.Weather.Get;
import com.mystery0.imystery0.Weather.GetTemp;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by myste on 2016-6-2-0002.
 * 主视图
 */
public class MainActivity extends Activity implements View.OnClickListener, ILocationCallback, AdapterView.OnItemClickListener
{
    private String District;
    private LocationHelper location;
    private FindCode findCode;
    private String txt;

    private ListView listView;
    private TextView title;
    private TextView date;
    private ImageView img_code;
    private ImageView img_refresh;

    /**
     * 未来六天天气预报
     */
    private TextView tmp_0;
    private TextView tmp_1;
    private TextView tmp_2;
    private TextView tmp_3;
    private TextView tmp_4;
    private TextView tmp_5;
    private TextView tmp_6;

    private TextView time_1;
    private TextView time_2;
    private TextView time_3;
    private TextView time_4;
    private TextView time_5;
    private TextView time_6;

    private ImageView code_1;
    private ImageView code_2;
    private ImageView code_3;
    private ImageView code_4;
    private ImageView code_5;
    private ImageView code_6;

    /**
     * 静态常量
     */
    public static final int TXT = 0;
    public static final int DONE = 1;
    public static final int LOCATION = 2;
    private static final int REQUEST = 3;

    public static final int TMP_0 = 4;
    public static final int TMP_1 = 5;
    public static final int TMP_2 = 6;
    public static final int TMP_3 = 7;
    public static final int TMP_4 = 8;
    public static final int TMP_5 = 9;
    public static final int TMP_6 = 10;

    public static final int DATE_0 = 11;
    public static final int DATE_1 = 12;
    public static final int DATE_2 = 13;
    public static final int DATE_3 = 14;
    public static final int DATE_4 = 15;
    public static final int DATE_5 = 16;
    public static final int DATE_6 = 17;

    public static final int CODE_0 = 18;
    public static final int CODE_1 = 19;
    public static final int CODE_2 = 20;
    public static final int CODE_3 = 21;
    public static final int CODE_4 = 22;
    public static final int CODE_5 = 23;
    public static final int CODE_6 = 24;

    /**
     * Handler消息接收判断
     */
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("temp", MODE_PRIVATE);
            GetTemp getTemp = new GetTemp(sharedPreferences);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (msg.what)
            {
                case LOCATION:
                    title.setText(getTemp.getDistrict());
                    editor.putString("district", District);
                    editor.apply();
                    break;
                case DONE:
                    Toast.makeText(MainActivity.this, "自动定位完成!", Toast.LENGTH_SHORT).show();
                    break;
                case TXT:
                    txt = (String) msg.obj;
                    break;

                case CODE_0:
                    img_code.setImageResource(Get.get(getTemp.getimg_code()));
                    editor.putString("img_code", (String) msg.obj);
                    editor.apply();
                    break;
                case CODE_1:
                    code_1.setImageResource(Get.get(getTemp.getCode_1()));
                    editor.putString("code_1", (String) msg.obj);
                    editor.apply();
                    break;
                case CODE_2:
                    code_2.setImageResource(Get.get(getTemp.getCode_2()));
                    editor.putString("code_2", (String) msg.obj);
                    editor.apply();
                    break;
                case CODE_3:
                    code_3.setImageResource(Get.get(getTemp.getCode_3()));
                    editor.putString("code_3", (String) msg.obj);
                    editor.apply();
                    break;
                case CODE_4:
                    code_4.setImageResource(Get.get(getTemp.getCode_4()));
                    editor.putString("code_4", (String) msg.obj);
                    editor.apply();
                    break;
                case CODE_5:
                    code_5.setImageResource(Get.get(getTemp.getCode_5()));
                    editor.putString("code_5", (String) msg.obj);
                    editor.apply();
                    break;
                case CODE_6:
                    code_6.setImageResource(Get.get(getTemp.getCode_6()));
                    editor.putString("code_6", (String) msg.obj);
                    editor.apply();
                    break;

                case TMP_0:
                    tmp_0.setText(getTemp.getnowtmp());
                    editor.putString("tmp_0", msg.obj + "  " + txt);
                    editor.apply();
                    break;
                case TMP_1:
                    tmp_1.setText(getTemp.gettmp_1());
                    editor.putString("tmp_1", (String) msg.obj);
                    editor.apply();
                    break;
                case TMP_2:
                    tmp_2.setText(getTemp.gettmp_2());
                    editor.putString("tmp_2", (String) msg.obj);
                    editor.apply();
                    break;
                case TMP_3:
                    tmp_3.setText(getTemp.gettmp_3());
                    editor.putString("tmp_3", (String) msg.obj);
                    editor.apply();
                    break;
                case TMP_4:
                    tmp_4.setText(getTemp.gettmp_4());
                    editor.putString("tmp_4", (String) msg.obj);
                    editor.apply();
                    break;
                case TMP_5:
                    tmp_5.setText(getTemp.gettmp_5());
                    editor.putString("tmp_5", (String) msg.obj);
                    editor.apply();
                    break;
                case TMP_6:
                    tmp_6.setText(getTemp.gettmp_6());
                    editor.putString("tmp_6", (String) msg.obj);
                    editor.apply();
                    break;

                case DATE_0:
                    date.setText(getTemp.getdate());
                    editor.putString("date", (String) msg.obj);
                    editor.apply();
                    break;
                case DATE_1:
                    time_1.setText(getTemp.getdate_1());
                    editor.putString("date_1", (String) msg.obj);
                    editor.apply();
                    break;
                case DATE_2:
                    time_2.setText(getTemp.getdate_2());
                    editor.putString("date_2", (String) msg.obj);
                    editor.apply();
                    break;
                case DATE_3:
                    time_3.setText(getTemp.getdate_3());
                    editor.putString("date_3", (String) msg.obj);
                    editor.apply();
                    break;
                case DATE_4:
                    time_4.setText(getTemp.getdate_4());
                    editor.putString("date_4", (String) msg.obj);
                    editor.apply();
                    break;
                case DATE_5:
                    time_5.setText(getTemp.getdate_5());
                    editor.putString("date_5", (String) msg.obj);
                    editor.apply();
                    break;
                case DATE_6:
                    time_6.setText(getTemp.getdate_6());
                    editor.putString("date_6", (String) msg.obj);
                    editor.apply();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        /**
         * 手动定位
         */
        title.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        img_refresh.setOnClickListener(this);
    }

    /**
     * 初始化
     */
    private void initialization()
    {
        Bmob.initialize(this, "7316434f9448bb798f410da5d00b1b1c");
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        BmobUpdateAgent.update(this);
        /**
         * 调用更新检测
         */
        BmobInstallation.getCurrentInstallation(this).save();
        BmobPush.startWork(this);

        /**
         * 绑定视图
         */
        listView = (ListView) findViewById(R.id.list_left_drawer);
        title = (TextView) findViewById(R.id.location);
        date = (TextView) findViewById(R.id.date);
        img_refresh = (ImageView) findViewById(R.id.refresh);

        time_1 = (TextView) findViewById(R.id.time_1);
        time_2 = (TextView) findViewById(R.id.time_2);
        time_3 = (TextView) findViewById(R.id.time_3);
        time_4 = (TextView) findViewById(R.id.time_4);
        time_5 = (TextView) findViewById(R.id.time_5);
        time_6 = (TextView) findViewById(R.id.time_6);
        tmp_0 = (TextView) findViewById(R.id.temperature);
        tmp_1 = (TextView) findViewById(R.id.tmp_1);
        tmp_2 = (TextView) findViewById(R.id.tmp_2);
        tmp_3 = (TextView) findViewById(R.id.tmp_3);
        tmp_4 = (TextView) findViewById(R.id.tmp_4);
        tmp_5 = (TextView) findViewById(R.id.tmp_5);
        tmp_6 = (TextView) findViewById(R.id.tmp_6);
        img_code = (ImageView) findViewById(R.id.img_weather);
        code_1 = (ImageView) findViewById(R.id.img_1);
        code_2 = (ImageView) findViewById(R.id.img_2);
        code_3 = (ImageView) findViewById(R.id.img_3);
        code_4 = (ImageView) findViewById(R.id.img_4);
        code_5 = (ImageView) findViewById(R.id.img_5);
        code_6 = (ImageView) findViewById(R.id.img_6);

        SharedPreferences sharedPreferences = getSharedPreferences("temp", MODE_PRIVATE);
        GetTemp getTemp = new GetTemp(sharedPreferences);

        /**
         * 读取缓存
         */
        title.setText(getTemp.getDistrict());
        date.setText(getTemp.getdate());
        time_1.setText(getTemp.getdate_1());
        time_2.setText(getTemp.getdate_2());
        time_3.setText(getTemp.getdate_3());
        time_4.setText(getTemp.getdate_4());
        time_5.setText(getTemp.getdate_5());
        time_6.setText(getTemp.getdate_6());
        tmp_0.setText(getTemp.getnowtmp());
        tmp_1.setText(getTemp.gettmp_1());
        tmp_2.setText(getTemp.gettmp_2());
        tmp_3.setText(getTemp.gettmp_3());
        tmp_4.setText(getTemp.gettmp_4());
        tmp_5.setText(getTemp.gettmp_5());
        tmp_6.setText(getTemp.gettmp_6());
        img_code.setImageResource(Get.get(getTemp.getimg_code()));
        code_1.setImageResource(Get.get(getTemp.getCode_1()));
        code_2.setImageResource(Get.get(getTemp.getCode_2()));
        code_3.setImageResource(Get.get(getTemp.getCode_3()));
        code_4.setImageResource(Get.get(getTemp.getCode_4()));
        code_5.setImageResource(Get.get(getTemp.getCode_5()));
        code_6.setImageResource(Get.get(getTemp.getCode_6()));

        final SimpleAdapter adapter = new SimpleAdapter(
                this, AdapterToList.getList(), R.layout.item_nenu, new String[]{"pic", "text"}, new int[]{R.id.menu_image, R.id.menu_text}
        );
        listView.setAdapter(adapter);

        /**
         * 启动自动定位
         */
        location = new LocationHelper(this, this.getApplicationContext());
        location.startLocation();//获取位置信息
        findCode = new FindCode();
    }

    /**
     * 定位完成与否监听
     *
     * @param info 县名称
     */
    @Override
    public void onLocationChanged(String info)
    {
        if (District == info)
            location.stopLocation();
        else
        {
            District = info;
            title.setText(info);
            String city_code = findCode.Find_Code(this.getApplicationContext(), District);
            getWeatherCode(city_code);
            Message message = new Message();
            message.what = DONE;
            handler.sendMessage(message);
        }
    }

    @Override
    public void onLocationError(String info)
    {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 标题点击事件,用于手动定位
     */
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.location:
                Intent intent = new Intent(MainActivity.this, Province_List.class);
                startActivityForResult(intent, REQUEST);
                break;
            case R.id.refresh:
                String city_code = findCode.Find_Code(this.getApplicationContext(), title.getText().toString());
                Log.i("info", city_code);
                getWeatherCode(city_code);
                Message message = new Message();
                message.what = DONE;
                handler.sendMessage(message);
        }
    }

    /**
     * listView点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        switch (position + 1)
        {
            case 1:
                startActivity(new Intent(MainActivity.this, Music_Activity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, chat_Activity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, Feedback_Activity.class));
                break;
            case 4:
                //startActivity(new Intent(MainActivity.this,Setting.class));
                break;
        }
    }

    /**
     * 手动定位位置activity销毁之后回调
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST)
            if (resultCode == RESULT_OK)
            {
                District = data.getStringExtra("code");
                Log.i("info", District);
                Message message = new Message();
                message.what = LOCATION;
                message.obj = District;
                handler.sendMessage(message);
                String city_code = findCode.Find_Code(this.getApplicationContext(), District);
                getWeatherCode(city_code);
            }
    }

    /**
     * 获取天气api返回全部代码
     * 解析返回的数据
     */

    public void getWeatherCode(final String code)//获取全部数据
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String text = null;
                HttpURLConnection conn = null;
                try
                {
                    URL url = new URL("https://api.heweather.com/x3/weather?cityid=" + code + "&key=1e33a382edbd4f328a2f8a2d2472a56c");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.connect();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));//获得输入流的包装流
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        response.append(line);
                    }
                    text = response.toString();

                } catch (java.io.IOException e)
                {
                    e.printStackTrace();
                } finally
                {
                    if (conn != null)
                    {
                        conn.disconnect();
                    }
                }
                /**
                 * 解析数据
                 */
                try
                {
                    JSONObject jsonObject = new JSONObject(text);
                    getNow(jsonObject.getString("HeWeather data service 3.0"));
                    getDailyForecast(jsonObject.getString("HeWeather data service 3.0"));
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getNow(String text)//获取now 实况天气
    {
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                getCond(jsonObject.getString("now"));
                getTmp(jsonObject.getString("now"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void getDailyForecast(String text)//获取daily_forecast 天气预报
    {
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                getDay_0(jsonObject.getString("daily_forecast"));
                getDay_1(jsonObject.getString("daily_forecast"));
                getDay_2(jsonObject.getString("daily_forecast"));
                getDay_3(jsonObject.getString("daily_forecast"));
                getDay_4(jsonObject.getString("daily_forecast"));
                getDay_5(jsonObject.getString("daily_forecast"));
                getDay_6(jsonObject.getString("daily_forecast"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void getDay_0(String text)//获取今日信息
    {
        String date = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            date = jsonObject.getString("date");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message1 = new Message();
        message1.what = DATE_0;
        message1.obj = date;
        handler.sendMessage(message1);
    }

    private void getDay_1(String text)
    {
        String date = null;
        String tmp = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(1);
            date = jsonObject.getString("date");
            JSONObject today = new JSONObject(jsonObject.getString("tmp"));
            tmp = today.getString("min") + "℃-----" + today.getString("max") + "℃";
            getCode_1(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message1 = new Message();
        Message message2 = new Message();
        message1.what = DATE_1;
        message1.obj = date;
        message2.what = TMP_1;
        message2.obj = tmp;
        handler.sendMessage(message1);
        handler.sendMessage(message2);
    }

    private void getDay_2(String text)
    {
        String date = null;
        String tmp = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(2);
            date = jsonObject.getString("date");
            JSONObject today = new JSONObject(jsonObject.getString("tmp"));
            tmp = today.getString("min") + "℃-----" + today.getString("max") + "℃";
            getCode_2(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message1 = new Message();
        Message message2 = new Message();
        message1.what = DATE_2;
        message1.obj = date;
        message2.what = TMP_2;
        message2.obj = tmp;
        handler.sendMessage(message1);
        handler.sendMessage(message2);
    }

    private void getDay_3(String text)
    {
        String date = null;
        String tmp = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(3);
            date = jsonObject.getString("date");
            JSONObject today = new JSONObject(jsonObject.getString("tmp"));
            tmp = today.getString("min") + "℃-----" + today.getString("max") + "℃";
            getCode_3(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message1 = new Message();
        Message message2 = new Message();
        message1.what = DATE_3;
        message1.obj = date;
        message2.what = TMP_3;
        message2.obj = tmp;
        handler.sendMessage(message1);
        handler.sendMessage(message2);
    }

    private void getDay_4(String text)
    {
        String date = null;
        String tmp = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(4);
            date = jsonObject.getString("date");
            JSONObject today = new JSONObject(jsonObject.getString("tmp"));
            tmp = today.getString("min") + "℃-----" + today.getString("max") + "℃";
            getCode_4(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message1 = new Message();
        Message message2 = new Message();
        message1.what = DATE_4;
        message1.obj = date;
        message2.what = TMP_4;
        message2.obj = tmp;
        handler.sendMessage(message1);
        handler.sendMessage(message2);
    }

    private void getDay_5(String text)
    {
        String date = null;
        String tmp = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(5);
            date = jsonObject.getString("date");
            JSONObject today = new JSONObject(jsonObject.getString("tmp"));
            tmp = today.getString("min") + "℃-----" + today.getString("max") + "℃";
            getCode_5(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message1 = new Message();
        Message message2 = new Message();
        message1.what = DATE_5;
        message1.obj = date;
        message2.what = TMP_5;
        message2.obj = tmp;
        handler.sendMessage(message1);
        handler.sendMessage(message2);
    }

    private void getDay_6(String text)
    {
        String date = null;
        String tmp = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(6);
            date = jsonObject.getString("date");
            JSONObject today = new JSONObject(jsonObject.getString("tmp"));
            tmp = today.getString("min") + "℃-----" + today.getString("max") + "℃";
            getCode_6(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message1 = new Message();
        Message message2 = new Message();
        message1.what = DATE_6;
        message1.obj = date;
        message2.what = TMP_6;
        message2.obj = tmp;
        handler.sendMessage(message1);
        handler.sendMessage(message2);
    }

    private void getCond(String text)//获取cond 天气状况
    {
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            getTxt(jsonObject.getString("cond"));
            getCode(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void getTxt(String text)//获取txt 天气描述
    {
        String weather_txt = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            weather_txt = jsonObject.getString("txt");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = TXT;
        message.obj = weather_txt;
        handler.sendMessage(message);
    }

    private void getTmp(String text)//获取tmp 当前温度(摄氏度)
    {
        String nowTmp = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            nowTmp = jsonObject.getString("tmp") + "℃";
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = TMP_0;
        message.obj = nowTmp;
        handler.sendMessage(message);
    }

    private void getCode(String text)//获取code 天气代码
    {
        String img_code = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            img_code = "tt" + jsonObject.getString("code");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = CODE_0;
        message.obj = img_code;
        handler.sendMessage(message);
        Message message1 = new Message();
        message1.what = LOCATION;
        handler.sendMessage(message1);
    }

    private void getCode_1(String text)
    {

        String img_code = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            img_code = "tt" + jsonObject.getString("code_d");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = CODE_1;
        message.obj = img_code;
        handler.sendMessage(message);
    }

    private void getCode_2(String text)
    {

        String img_code = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            img_code = "tt" + jsonObject.getString("code_d");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = CODE_2;
        message.obj = img_code;
        handler.sendMessage(message);
    }

    private void getCode_3(String text)
    {

        String img_code = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            img_code = "tt" + jsonObject.getString("code_d");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = CODE_3;
        message.obj = img_code;
        handler.sendMessage(message);
    }

    private void getCode_4(String text)
    {

        String img_code = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            img_code = "tt" + jsonObject.getString("code_d");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = CODE_4;
        message.obj = img_code;
        handler.sendMessage(message);
    }

    private void getCode_5(String text)
    {

        String img_code = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            img_code = "tt" + jsonObject.getString("code_d");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = CODE_5;
        message.obj = img_code;
        handler.sendMessage(message);
    }

    private void getCode_6(String text)
    {

        String img_code = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            img_code = "tt" + jsonObject.getString("code_d");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = CODE_6;
        message.obj = img_code;
        handler.sendMessage(message);
    }
}

