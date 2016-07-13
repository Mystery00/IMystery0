package com.mystery0.imystery0.BaseClass;


import cn.bmob.v3.BmobObject;

/**
 * Created by myste on 2016-7-11-0011.
 * 头像类的上传与下载
 */
public class HeadFile extends BmobObject
{
    private String username;
    private String headFilePath;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setHeadFile(String headFilePath)
    {
        this.headFilePath = headFilePath;
    }

    public String getHeadFilePath()
    {
        return headFilePath;
    }
}
