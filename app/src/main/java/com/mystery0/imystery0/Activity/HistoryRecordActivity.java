package com.mystery0.imystery0.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mystery0.imystery0.Adapter.HistoryAdapter;
import com.mystery0.imystery0.BaseClass.Message;
import com.mystery0.imystery0.DatebaseHelper.RecordSQLiteOpenHelper;
import com.mystery0.imystery0.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecordActivity extends Activity implements View.OnClickListener
{
    private ImageButton back;
    private TextView textDelete;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initView();

        back.setOnClickListener(this);
        textDelete.setOnClickListener(this);
    }

    private void initView()
    {
        ListView listView = (ListView) findViewById(R.id.listRecord);
        back = (ImageButton) findViewById(R.id.back_record);
        textDelete = (TextView) findViewById(R.id.text_delete);
        List<Message> messageList = getMsgList();
        adapter = new HistoryAdapter(messageList, this.getApplicationContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.back_record:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.text_delete:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("正在删除......");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                DeleteHistory();
                setResult(RESULT_OK, intent);
                progressDialog.dismiss();
                finish();
                break;
        }
    }

    private void DeleteHistory()
    {
        RecordSQLiteOpenHelper recordSQLiteOpenHelper = new RecordSQLiteOpenHelper(HistoryRecordActivity.this, "history_list.db");
        SQLiteDatabase db = recordSQLiteOpenHelper.getWritableDatabase();
        db.delete("history_list", null, null);
        db.close();
        adapter.notifyDataSetChanged();
    }

    private List<Message> getMsgList()
    {
        ArrayList<Message> messageArrayList = new ArrayList<>();
        RecordSQLiteOpenHelper recordSQLiteOpenHelper = new RecordSQLiteOpenHelper(this, "history_list.db");
        SQLiteDatabase db = recordSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("history_list", new String[]{"content", "type"}, null, null, null, null, null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                Message message = new Message(cursor.getString(cursor.getColumnIndex("content")), cursor.getInt(cursor.getColumnIndex("type")));
                messageArrayList.add(message);
            }
            cursor.close();
        } else
            Log.e("error", "空指针!!!!");
        db.close();
        return messageArrayList;
    }
}
