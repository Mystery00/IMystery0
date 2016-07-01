package com.mystery0.imystery0.HistoryRecord;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mystery0.imystery0.Chat_Online.Msg;
import com.mystery0.imystery0.R;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends Activity implements View.OnClickListener
{
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initialization();

        back.setOnClickListener(this);
    }

    private void initialization()
    {
        ListView listView = (ListView) findViewById(R.id.listRecord);
        back = (ImageButton) findViewById(R.id.back_record);
        List<Msg> msgList = getMsgList();
        HistoryAdapter adapter = new HistoryAdapter(msgList, this.getApplicationContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        finish();
    }

    private List<Msg> getMsgList()
    {
        ArrayList<Msg> msgArrayList = new ArrayList<>();
        RecordSQLiteOpenHelper recordSQLiteOpenHelper = new RecordSQLiteOpenHelper(this, "history_list.db");
        SQLiteDatabase db = recordSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("history_list", new String[]{"content", "type"}, null, null, null, null, null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                Msg msg = new Msg(cursor.getString(cursor.getColumnIndex("content")), cursor.getInt(cursor.getColumnIndex("type")));
                msgArrayList.add(msg);
            }
            cursor.close();
        } else
            Log.e("error", "空指针!!!!");
        db.close();
        return msgArrayList;
    }
}
