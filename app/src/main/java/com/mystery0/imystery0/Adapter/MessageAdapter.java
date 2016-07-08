package com.mystery0.imystery0.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mystery0.imystery0.BaseClass.Message;
import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-6-28-0028.
 * 消息加载器
 */
public class MessageAdapter extends ArrayAdapter<Message>
{
    private int resourceId;
    private LinearLayout leftLayout;
    private LinearLayout rightLayout;
    private TextView leftText;
    private TextView rightText;

    public MessageAdapter(Context context, int resource, List<Message> objects)
    {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Message message = getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            leftLayout = (LinearLayout) convertView.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) convertView.findViewById(R.id.right_layout);
            leftText = (TextView) convertView.findViewById(R.id.text_left);
            rightText = (TextView) convertView.findViewById(R.id.text_right);
        }
        if (message.getType() == Message.RECEIVED)
        {
            leftLayout.setVisibility(View.VISIBLE);
            rightLayout.setVisibility(View.GONE);
            leftText.setText(message.getContent());
        } else if (message.getType() == Message.SEND)
        {
            leftLayout.setVisibility(View.GONE);
            rightLayout.setVisibility(View.VISIBLE);
            rightText.setText(message.getContent());
        }
        return convertView;
    }
}
