package com.mystery0.imystery0.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mystery0.imystery0.BaseClass.FeedBack;
import com.mystery0.imystery0.PublicMethod.GetErrorInfo;
import com.mystery0.imystery0.R;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by myste on 2016-6-5-0005.
 * 意见反馈主界面
 */
public class FeedbackActivity extends Activity implements View.OnClickListener
{
    private EditText body;
    private EditText userinfo;
    private FeedBack feedBack;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        Bmob.initialize(this, "7316434f9448bb798f410da5d00b1b1c");
        BmobPush.startWork(this);

        Button send = (Button) findViewById(R.id.feed_back_button);
        body=(EditText)findViewById(R.id.editText_body);
        userinfo=(EditText)findViewById(R.id.editText_user);
        ImageView back = (ImageView) findViewById(R.id.back_logo);
        feedBack =new FeedBack();

        send.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    public void sendFeedBack(String msg)
    {
        feedBack.setContent(msg);
        feedBack.save(this, new SaveListener()
        {
            @Override
            public void onSuccess()
            {
                Log.i("bmob", "反馈信息已保存到服务器");
                Toast.makeText(FeedbackActivity.this, "反馈信息提交成功!感谢您的支持!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s)
            {
                GetErrorInfo.getErrorInfo(FeedbackActivity.this, s);
                Log.e("info", "错误代码:" + i + "错误原因:" + s);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.feed_back_button:
                ProgressDialog progressDialog = new ProgressDialog(FeedbackActivity.this);
                progressDialog.setMessage("正在提交您的反馈...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                String content = body.getText().toString() + "---------------联系方式:" + userinfo.getText().toString();
                if (content.equals(msg))
                {
                    Toast.makeText(FeedbackActivity.this, "请勿重复提交反馈", Toast.LENGTH_SHORT).show();
                } else
                {
                    msg = content;
                    // 发送反馈信息
                    sendFeedBack(content);
                    progressDialog.dismiss();
                }
                break;
            case R.id.back_logo:
                finish();
                break;
        }
    }
}
