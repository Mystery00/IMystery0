package com.mystery0.imystery0.Chat_Online;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mystery0.imystery0.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class chat_Activity extends Activity implements View.OnClickListener
{
    private ListView listView;
    private EditText text;
    private ImageButton sendButton;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList = new ArrayList<>();
    public static final int RECEIVED = 0;
    public static final int SEND = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_layout);

        initialization();

        sendButton.setOnClickListener(this);
    }

    private void initialization()
    {
        listView = (ListView) findViewById(R.id.listView);
        text = (EditText) findViewById(R.id.chat_text);
        sendButton = (ImageButton) findViewById(R.id.send);
        msgAdapter = new MsgAdapter(chat_Activity.this, R.layout.msg_item, msgList);
        listView.setAdapter(msgAdapter);
    }

    @Override
    public void onClick(View v)
    {
        String content = text.getText().toString();
        if (!content.equals(""))
        {
            Message message = new Message();
            message.what = SEND;
            message.obj = content;
            handler.sendMessage(message);
            GetMessage(content);
        }
    }

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message message)
        {
            switch (message.what)
            {
                case RECEIVED:
                    Msg msg1 = new Msg((String) message.obj, Msg.RECEIVED);
                    msgList.add(msg1);
                    msgAdapter.notifyDataSetChanged();
                    listView.setSelection(msgList.size());
                    break;
                case SEND:
                    Msg msg2 = new Msg((String) message.obj, Msg.SEND);
                    msgList.add(msg2);
                    msgAdapter.notifyDataSetChanged();
                    listView.setSelection(msgList.size());
                    text.setText("");
                    break;
            }
        }
    };

    public void GetMessage(final String content)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                HttpURLConnection connection = null;
                String text = null;
                try
                {
                    URL url = new URL("http://www.tuling123.com/openapi/api?key=a1d749c50677714e45b6b5a0f33c04e6&info=" + content);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
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
                    if (connection != null)
                    {
                        connection.disconnect();
                    }
                }
                try
                {
                    JSONObject jsonObject = new JSONObject(text);
                    Message message = new Message();
                    message.obj = jsonObject.getString("text");
                    message.what = RECEIVED;
                    handler.sendMessage(message);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
