package com.mystery0.imystery0.Chat_Online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mystery0.imystery0.R;

public class chat_Activity extends AppCompatActivity
{
    private ListView listView;
    private EditText text;
    private ImageButton sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_layout);

        initialization();
    }

    private void initialization()
    {
        listView = (ListView) findViewById(R.id.listView);
        text = (EditText) findViewById(R.id.chat_text);
        sendButton = (ImageButton) findViewById(R.id.send);
    }
}
