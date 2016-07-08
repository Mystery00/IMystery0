package com.mystery0.imystery0.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mystery0.imystery0.BaseClass.Message;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-7-1-0001.
 * 历史记录适配器
 */
public class HistoryAdapter extends BaseAdapter
{
    private List<Message> messageList;
    private Context context;

    public HistoryAdapter(List<Message> messageList, Context context)
    {
        this.messageList = messageList;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return messageList.size();
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

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).
                inflate(R.layout.item_record, null);
        TextView text_person = (TextView) convertView.findViewById(R.id.text_person);
        TextView text_content = (TextView) convertView.findViewById(R.id.text_content);
        Message message = messageList.get(position);
        text_content.setText(message.getContent());
        switch (message.getType())
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
