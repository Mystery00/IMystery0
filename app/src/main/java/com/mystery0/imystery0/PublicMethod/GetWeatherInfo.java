package com.mystery0.imystery0.PublicMethod;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mystery0.imystery0.Activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by myste on 2016-7-8-0008.
 * 获取天气解析数据
 */
public class GetWeatherInfo
{
    private Handler handler;

    /**
     * 静态常量
     */
    public static final int TXT = 0;
    public static final int DONE = 1;
    public static final int LOCATION = 2;

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

    public GetWeatherInfo(Handler handler)
    {
        Log.i("info", "调用构造函数!");
        this.handler = handler;
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
