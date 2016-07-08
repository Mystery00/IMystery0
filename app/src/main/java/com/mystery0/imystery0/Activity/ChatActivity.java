package com.mystery0.imystery0.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mystery0.imystery0.PublicMethod.ChangeToUTF8;
import com.mystery0.imystery0.BaseClass.Message;
import com.mystery0.imystery0.Adapter.MessageAdapter;
import com.mystery0.imystery0.DatebaseHelper.RecordSQLiteOpenHelper;
import com.mystery0.imystery0.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends Activity implements View.OnClickListener
{
    private ListView listView;
    private EditText text;
    private ImageButton sendButton;
    private MessageAdapter messageAdapter;
    private TextView history;
    private ImageButton back;
    private List<Message> messageList = new ArrayList<>();
    public static final int RECEIVED = 0;
    public static final int SEND = 1;
    public static final int CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_layout);

        initView();

        sendButton.setOnClickListener(this);
        history.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initView()
    {
        listView = (ListView) findViewById(R.id.listView);
        text = (EditText) findViewById(R.id.chat_text);
        sendButton = (ImageButton) findViewById(R.id.send);
        history = (TextView) findViewById(R.id.history);
        back = (ImageButton) findViewById(R.id.back);
        messageAdapter = new MessageAdapter(ChatActivity.this, R.layout.item_msg, messageList);
        listView.setAdapter(messageAdapter);

        SharedPreferences preferences = getSharedPreferences("setting", MODE_PRIVATE);
        if (preferences.getString("image_chat", "null") != "null")
        {
            ImageView background = (ImageView) findViewById(R.id.music_background);
            File file = new File(preferences.getString("image_chat", ""));
            if (file.exists())
            {
                Bitmap bm = BitmapFactory.decodeFile(preferences.getString("image_chat", ""));
                background.setImageBitmap(bm);
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.send:
                String content = text.getText().toString();
                if (!content.equals(""))
                {
                    android.os.Message message = new android.os.Message();
                    message.what = SEND;
                    message.obj = content;
                    handler.sendMessage(message);
                    GetMessage(content);
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.history:
                startActivityForResult(new Intent(ChatActivity.this, HistoryRecordActivity.class), CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CODE)
            if (resultCode == RESULT_OK)
                messageAdapter.notifyDataSetChanged();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(android.os.Message message)
        {
            RecordSQLiteOpenHelper recordSQLiteOpenHelper = new RecordSQLiteOpenHelper(ChatActivity.this, "history_list.db");
            SQLiteDatabase db = recordSQLiteOpenHelper.getWritableDatabase();
            switch (message.what)
            {
                case RECEIVED:
                    Message message1 = new Message((String) message.obj, Message.RECEIVED);
                    messageList.add(message1);
                    messageAdapter.notifyDataSetChanged();
                    listView.setSelection(messageList.size());
                    ContentValues values1 = new ContentValues();
                    values1.put("content", message1.getContent());
                    values1.put("type", message1.getType());
                    db.insert("history_list", null, values1);
                    db.close();
                    break;
                case SEND:
                    Message message2 = new Message((String) message.obj, Message.SEND);
                    messageList.add(message2);
                    messageAdapter.notifyDataSetChanged();
                    listView.setSelection(messageList.size());
                    text.setText("");
                    ContentValues values2 = new ContentValues();
                    values2.put("content", message2.getContent());
                    values2.put("type", message2.getType());
                    db.insert("history_list", null, values2);
                    db.close();
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
                String c = ChangeToUTF8.convertStringToUTF8(content);
                try
                {
                    URL url = new URL("http://www.tuling123.com/openapi/api?key=a1d749c50677714e45b6b5a0f33c04e6&info=" + c);
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
                    android.os.Message message = new android.os.Message();
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
