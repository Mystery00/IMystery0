package com.mystery0.imystery0.BaseClass;


/**
 * Created by myste on 2016-6-26-0026.
 * Msg类
 */
public class Message
{
    private String content;
    public static final int RECEIVED = 0;
    public static final int SEND = 1;
    private int type;

    public Message(String content, int type)
    {
        this.content = content;
        this.type = type;
    }

    public String getContent()
    {
        return content;
    }

    public int getType()
    {
        return type;
    }
}
