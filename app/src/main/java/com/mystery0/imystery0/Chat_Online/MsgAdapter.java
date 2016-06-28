package com.mystery0.imystery0.Chat_Online;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mystery0.imystery0.R;

import java.util.List;

/**
 * Created by myste on 2016-6-28-0028.
 */
public class MsgAdapter extends ArrayAdapter<Msg>
{
    private int resourceId;

    public MsgAdapter(Context context, int resource, List<Msg> objects)
    {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftText = (TextView) view.findViewById(R.id.text_left);
            viewHolder.rightText = (TextView) view.findViewById(R.id.text_right);
            view.setTag(viewHolder);
        } else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (msg.getType() == Msg.RECEIVED)
        {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftText.setText(msg.getContent());
        } else if (msg.getType() == Msg.SEND)
        {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightText.setText(msg.getContent());
        }
        return view;
    }
}

class ViewHolder
{
    LinearLayout leftLayout;
    LinearLayout rightLayout;
    TextView leftText;
    TextView rightText;
}
