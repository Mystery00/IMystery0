package com.mystery0.imystery0.Feed_back;

import cn.bmob.v3.BmobObject;

/**
 * Created by myste on 2016-6-6-0006.
 * 意见反馈
 */
public class FeedBack extends BmobObject
{
    //反馈内容
    private String content;
    public String getContent()
    {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
