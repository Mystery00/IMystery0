package com.mystery0.imystery0.splash_viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mystery0.imystery0.log_in_java.log_in;
import com.mystery0.imystery0.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by myste on 2016-6-2-0002.
 */
public class viewpager extends Activity
{
    private ImageButton intent;
    private ViewPager viewPager;
    private List<View> viewList;
    private LinearLayout mLinearLayout;
    private View mView ;
    private int diatance;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_points);//小圆点列表
        mView = findViewById(R.id.v_redpoint);//小红点,代表当前选中页

        viewPager.setPageTransformer(true,new DepthPageTransformer());//设置转场动画
        LayoutInflater inflater= LayoutInflater.from(this);//获取页面
        viewList = new ArrayList<View>();

        Log.i("info","viewpager启动!!!");

        viewList.add(inflater.inflate(R.layout.viewpager_1,null));
        viewList.add(inflater.inflate(R.layout.viewpager_2,null));
        View view=inflater.inflate(R.layout.viewpager_last,null);
        viewList.add(view);

        Set_Res();

        intent=(ImageButton)view.findViewById(R.id.viewpager_last_intent);
        intent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(viewpager.this, log_in.class));
                finish();
            }
        });
        PagerAdapter pagerAdapter=new PagerAdapter()
        {
            @Override
            public int getCount()//获取页面数量
            {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object)//判断view和object是否相同
            {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object)//删除页面
            {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position)//添加页面
            {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public int getItemPosition(Object object)
            {
                return super.getItemPosition(object);
            }
        };
        viewPager.setAdapter(pagerAdapter);//加载适配器
        initEvent();
    }
    public void Set_Res()
    {
        /**
         * 此处4为引导页数量
         */
        for(int i = 0;i < 3 ; i ++)
        {
            //添加底部灰点
            View v = new View(getApplicationContext());
            v.setBackgroundResource(R.drawable.point_white);
            //指定其大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0)
                params.leftMargin = 20;
            v.setLayoutParams(params);
            mLinearLayout.addView(v);
        }
    }
    public void initEvent()
    {
        /**
         * 当底部红色小圆点加载完成时测出两个小灰点的距离，便于计算后面小红点动态移动的距离
         */
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                diatance =  mLinearLayout.getChildAt(1).getLeft() - mLinearLayout.getChildAt(0).getLeft();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                //测出页面滚动时小红点移动的距离，并通过setLayoutParams(params)不断更新其位置
                position = position % viewList.size();
                float leftMargin = diatance * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
                params.leftMargin = Math.round(leftMargin);
                mView.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }
}
