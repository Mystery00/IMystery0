package com.mystery0.imystery0.Weather;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mystery0.imystery0.Location.ILocationCallback;
import com.mystery0.imystery0.Location.List.Province_list;
import com.mystery0.imystery0.Location.LocationHelper;
import com.mystery0.imystery0.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather extends Activity implements View.OnClickListener, ILocationCallback
{
    private String District;
    private LocationHelper location;
    private FindCode findCode;
    private TextView title;
    private TextView date;
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
    private TextView nowtmp;
    private ImageView img_code;
    private ImageView code_1;
    private ImageView code_2;
    private ImageView code_3;
    private ImageView code_4;
    private ImageView code_5;
    private ImageView code_6;
    public static final int DATE_0 = 2;
    public static final int TXT = 4;
    public static final int NOWTMP = 5;
    public static final int CODE = 6;
    public static final int DONE = 7;
    public static final int LOCATION = 8;
    public static final int DATE_1 = 9;
    public static final int TMP_1 = 10;
    public static final int DATE_2 = 11;
    public static final int TMP_2 = 12;
    public static final int DATE_3 = 13;
    public static final int TMP_3 = 14;
    public static final int DATE_4 = 15;
    public static final int TMP_4 = 16;
    public static final int DATE_5 = 17;
    public static final int TMP_5 = 18;
    public static final int DATE_6 = 19;
    public static final int TMP_6 = 20;
    public static final int CODE_1 = 21;
    public static final int CODE_2 = 22;
    public static final int CODE_3 = 23;
    public static final int CODE_4 = 24;
    public static final int CODE_5 = 25;
    public static final int CODE_6 = 26;
    private static final int REQUST = 0;

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
                case DATE_0:
                    date.setText(getTemp.getdate());
                    editor.putString("date", (String) msg.obj);
                    editor.apply();
                    break;
                case NOWTMP:
                    nowtmp.setText(getTemp.getnowtmp());
                    editor.putString("nowtmp", (String) msg.obj);
                    editor.apply();
                    break;
                case CODE:
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
                case DONE:
                    Toast.makeText(Weather.this, "自动定位完成!", Toast.LENGTH_SHORT).show();
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
    }

    /**
     * 初始化
     */
    private void initialization()
    {
        /**
         * 绑定视图
         */
        title = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        time_1 = (TextView) findViewById(R.id.time_1);
        time_2 = (TextView) findViewById(R.id.time_2);
        time_3 = (TextView) findViewById(R.id.time_3);
        time_4 = (TextView) findViewById(R.id.time_4);
        time_5 = (TextView) findViewById(R.id.time_5);
        time_6 = (TextView) findViewById(R.id.time_6);
        nowtmp = (TextView) findViewById(R.id.temperature);
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
        date.setText(getTemp.getdate());
        time_1.setText(getTemp.getdate_1());
        time_2.setText(getTemp.getdate_2());
        time_3.setText(getTemp.getdate_3());
        time_4.setText(getTemp.getdate_4());
        time_5.setText(getTemp.getdate_5());
        time_6.setText(getTemp.getdate_6());
        nowtmp.setText(getTemp.getnowtmp());
        tmp_1.setText(getTemp.gettmp_1());
        tmp_2.setText(getTemp.gettmp_2());
        tmp_3.setText(getTemp.gettmp_3());
        tmp_4.setText(getTemp.gettmp_4());
        tmp_5.setText(getTemp.gettmp_5());
        tmp_6.setText(getTemp.gettmp_6());
        img_code.setImageResource(Get.get(getTemp.getimg_code()));

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
            Log.i("info", city_code);
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
        Intent intent = new Intent(Weather.this, Province_list.class);
        startActivityForResult(intent, REQUST);
    }

    /**
     * 手动定位位置activity销毁之后回调
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUST)
            if (resultCode == RESULT_OK)
            {
                District = data.getStringExtra("code");
                Log.i("info", District);
                Message message = new Message();
                message.what = LOCATION;
                message.obj = District;
                handler.sendMessage(message);
                String city_code = findCode.Find_Code(this.getApplicationContext(), District);
                Log.i("info", city_code);
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
                //text="{\"HeWeather data service 3.0\":[{\"aqi\":{\"city\":{\"aqi\":\"104\",\"co\":\"1\",\"no2\":\"27\",\"o3\":\"193\",\"pm10\":\"75\",\"pm25\":\"97\",\"qlty\":\"良\",\"so2\":\"7\"}},\"basic\":{\"city\":\"北京\",\"cnty\":\"中国\",\"id\":\"CN101010100\",\"lat\":\"39.904000\",\"lon\":\"116.391000\",\"update\":{\"loc\":\"2016-06-23 18:51\",\"utc\":\"2016-06-23 10:51\"}},\"daily_forecast\":[{\"astro\":{\"sr\":\"04:46\",\"ss\":\"19:46\"},\"cond\":{\"code_d\":\"101\",\"code_n\":\"101\",\"txt_d\":\"多云\",\"txt_n\":\"多云\"},\"date\":\"2016-06-23\",\"hum\":\"12\",\"pcpn\":\"0.0\",\"pop\":\"0\",\"pres\":\"1003\",\"tmp\":{\"max\":\"35\",\"min\":\"21\"},\"vis\":\"10\",\"wind\":{\"deg\":\"304\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"2\"}},{\"astro\":{\"sr\":\"04:46\",\"ss\":\"19:46\"},\"cond\":{\"code_d\":\"101\",\"code_n\":\"101\",\"txt_d\":\"多云\",\"txt_n\":\"多云\"},\"date\":\"2016-06-24\",\"hum\":\"12\",\"pcpn\":\"0.0\",\"pop\":\"0\",\"pres\":\"1005\",\"tmp\":{\"max\":\"32\",\"min\":\"20\"},\"vis\":\"10\",\"wind\":{\"deg\":\"359\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"8\"}},{\"astro\":{\"sr\":\"04:47\",\"ss\":\"19:47\"},\"cond\":{\"code_d\":\"100\",\"code_n\":\"100\",\"txt_d\":\"晴\",\"txt_n\":\"晴\"},\"date\":\"2016-06-25\",\"hum\":\"10\",\"pcpn\":\"0.0\",\"pop\":\"0\",\"pres\":\"1005\",\"tmp\":{\"max\":\"34\",\"min\":\"22\"},\"vis\":\"10\",\"wind\":{\"deg\":\"319\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"1\"}},{\"astro\":{\"sr\":\"04:47\",\"ss\":\"19:47\"},\"cond\":{\"code_d\":\"101\",\"code_n\":\"101\",\"txt_d\":\"多云\",\"txt_n\":\"多云\"},\"date\":\"2016-06-26\",\"hum\":\"9\",\"pcpn\":\"0.0\",\"pop\":\"2\",\"pres\":\"1002\",\"tmp\":{\"max\":\"36\",\"min\":\"24\"},\"vis\":\"10\",\"wind\":{\"deg\":\"166\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"9\"}},{\"astro\":{\"sr\":\"04:47\",\"ss\":\"19:47\"},\"cond\":{\"code_d\":\"302\",\"code_n\":\"302\",\"txt_d\":\"雷阵雨\",\"txt_n\":\"雷阵雨\"},\"date\":\"2016-06-27\",\"hum\":\"17\",\"pcpn\":\"0.2\",\"pop\":\"60\",\"pres\":\"1003\",\"tmp\":{\"max\":\"32\",\"min\":\"23\"},\"vis\":\"10\",\"wind\":{\"deg\":\"191\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"1\"}},{\"astro\":{\"sr\":\"04:48\",\"ss\":\"19:47\"},\"cond\":{\"code_d\":\"302\",\"code_n\":\"302\",\"txt_d\":\"雷阵雨\",\"txt_n\":\"雷阵雨\"},\"date\":\"2016-06-28\",\"hum\":\"36\",\"pcpn\":\"9.3\",\"pop\":\"54\",\"pres\":\"1005\",\"tmp\":{\"max\":\"29\",\"min\":\"20\"},\"vis\":\"7\",\"wind\":{\"deg\":\"170\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"6\"}},{\"astro\":{\"sr\":\"04:48\",\"ss\":\"19:47\"},\"cond\":{\"code_d\":\"101\",\"code_n\":\"101\",\"txt_d\":\"多云\",\"txt_n\":\"多云\"},\"date\":\"2016-06-29\",\"hum\":\"54\",\"pcpn\":\"21.8\",\"pop\":\"50\",\"pres\":\"1005\",\"tmp\":{\"max\":\"31\",\"min\":\"21\"},\"vis\":\"10\",\"wind\":{\"deg\":\"166\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"9\"}}],\"hourly_forecast\":[{\"date\":\"2016-06-23 19:00\",\"hum\":\"24\",\"pop\":\"0\",\"pres\":\"1002\",\"tmp\":\"35\",\"wind\":{\"deg\":\"268\",\"dir\":\"西风\",\"sc\":\"微风\",\"spd\":\"13\"}},{\"date\":\"2016-06-23 22:00\",\"hum\":\"24\",\"pop\":\"0\",\"pres\":\"1004\",\"tmp\":\"33\",\"wind\":{\"deg\":\"308\",\"dir\":\"西北风\",\"sc\":\"微风\",\"spd\":\"14\"}}],\"now\":{\"cond\":{\"code\":\"104\",\"txt\":\"阴\"},\"fl\":\"31\",\"hum\":\"47\",\"pcpn\":\"0\",\"pres\":\"1002\",\"tmp\":\"30\",\"vis\":\"10\",\"wind\":{\"deg\":\"150\",\"dir\":\"西南风\",\"sc\":\"4-5\",\"spd\":\"21\"}},\"status\":\"ok\",\"suggestion\":{\"comf\":{\"brf\":\"较不舒适\",\"txt\":\"白天天气多云，同时会感到有些热，不很舒适。\"},\"cw\":{\"brf\":\"较适宜\",\"txt\":\"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"},\"drsg\":{\"brf\":\"炎热\",\"txt\":\"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。\"},\"flu\":{\"brf\":\"少发\",\"txt\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\"},\"sport\":{\"brf\":\"较适宜\",\"txt\":\"天气较好，但由于风力较大，推荐您在室内进行低强度运动，若在户外运动请注意避风。\"},\"trav\":{\"brf\":\"适宜\",\"txt\":\"天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！\"},\"uv\":{\"brf\":\"中等\",\"txt\":\"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\"}}}]}";
                /**
                 * 解析数据
                 */
                try
                {
                    JSONObject jsonObject = new JSONObject(text);
                    getnow(jsonObject.getString("HeWeather data service 3.0"));
                    getdaily_forecast(jsonObject.getString("HeWeather data service 3.0"));
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getnow(String text)//获取now 实况天气
    {
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                getcond(jsonObject.getString("now"));
                gettmp(jsonObject.getString("now"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void getdaily_forecast(String text)//获取daily_forecast 天气预报
    {
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                getday_0(jsonObject.getString("daily_forecast"));
                getday_1(jsonObject.getString("daily_forecast"));
                getday_2(jsonObject.getString("daily_forecast"));
                getday_3(jsonObject.getString("daily_forecast"));
                getday_4(jsonObject.getString("daily_forecast"));
                getday_5(jsonObject.getString("daily_forecast"));
                getday_6(jsonObject.getString("daily_forecast"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void getday_0(String text)//获取今日信息
    {
        String date = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(1);
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

    private void getday_1(String text)
    {
        String date = null;
        String tmp = null;
        try
        {
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            date = jsonObject.getString("date");
            JSONObject today = new JSONObject(jsonObject.getString("tmp"));
            tmp = today.getString("min") + "℃-----" + today.getString("max") + "℃";
            getcode_1(jsonObject.getString("cond"));
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

    private void getday_2(String text)
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
            getcode_2(jsonObject.getString("cond"));
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

    private void getday_3(String text)
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
            getcode_3(jsonObject.getString("cond"));
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

    private void getday_4(String text)
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
            getcode_4(jsonObject.getString("cond"));
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

    private void getday_5(String text)
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
            getcode_5(jsonObject.getString("cond"));
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

    private void getday_6(String text)
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
            getcode_6(jsonObject.getString("cond"));
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

    private void getcond(String text)//获取cond 天气状况
    {
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            gettxt(jsonObject.getString("cond"));
            getcode(jsonObject.getString("cond"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void gettxt(String text)//获取txt 天气描述
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

    private void gettmp(String text)//获取tmp 当前温度(摄氏度)
    {
        String nowtmp = null;
        try
        {
            JSONObject jsonObject = new JSONObject(text);
            nowtmp = "当前温度:" + jsonObject.getString("tmp") + "℃";
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = NOWTMP;
        message.obj = nowtmp;
        handler.sendMessage(message);
    }

    private void getcode(String text)//获取code 天气代码
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
        message.what = CODE;
        message.obj = img_code;
        handler.sendMessage(message);
        Message message1 = new Message();
        message1.what = LOCATION;
        handler.sendMessage(message1);
    }

    private void getcode_1(String text)
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

    private void getcode_2(String text)
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

    private void getcode_3(String text)
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

    private void getcode_4(String text)
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

    private void getcode_5(String text)
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

    private void getcode_6(String text)
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
