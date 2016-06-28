package com.mystery0.imystery0.Chat_Online;

/**
 * Created by myste on 2016-6-28-0028.
 */
public class Change
{
    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原串
     * @return
     */
    public static String convertStringToUTF8(String s)
    {
        if (s == null || s.equals(""))
        {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try
        {
            char c;
            for (int i = 0; i < s.length(); i++)
            {
                c = s.charAt(i);
                if (c <= 255)
                {
                    sb.append(c);
                } else
                {
                    byte[] b;

                    b = Character.toString(c).getBytes("utf-8");

                    for (byte aB : b)
                    {
                        int k = aB;
                        if (k < 0)
                            k += 256;
                        sb.append("%").append(Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();

        }
        return sb.toString();
    }
}
