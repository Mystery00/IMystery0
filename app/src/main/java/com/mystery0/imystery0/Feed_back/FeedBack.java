package com.mystery0.imystery0.Feed_back;

import cn.bmob.v3.BmobObject;

/**
 * Created by myste on 2016-6-6-0006.
 */
public class FeedBack extends BmobObject
{
    //反馈内容
    private String content;
    //联系方式
    private String contacts;
    public String getContent()
    {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContacts() {
        return contacts;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
