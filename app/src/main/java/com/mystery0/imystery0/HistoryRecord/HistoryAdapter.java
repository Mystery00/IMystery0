package com.mystery0.imystery0.HistoryRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mystery0.imystery0.Chat_Online.Msg;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-7-1-0001.
 */
public class HistoryAdapter extends BaseAdapter
{
    private List<Msg> msgList;
    private Context context;

    public HistoryAdapter(List<Msg> msgList, Context context)
    {
        this.msgList = msgList;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return msgList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).
                inflate(R.layout.item_record, null);
        TextView text_person = (TextView) convertView.findViewById(R.id.text_person);
        TextView text_content = (TextView) convertView.findViewById(R.id.text_content);
        Msg msg = msgList.get(position);
        text_content.setText(msg.getContent());
        switch (msg.getType())
        {
            case 0:
                text_person.setText("机器人:");
                break;
            case 1:
                text_person.setText("我:");
                break;
        }
        return convertView;
    }
}
