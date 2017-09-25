package com.snail.recyclerbanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.snail.banner.view.RecyclerBanner;
import com.snail.recyclerbanner.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //RecyclerBanner
    private RecyclerBanner mRecyclerBanner;

    //广告内容数据集合Data
    private List<Integer> mData;

    //广告内容适配器
    private BannerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        mData = new ArrayList<>();
        mAdapter = new BannerAdapter(mData);

        mRecyclerBanner = (RecyclerBanner) findViewById(R.id.banner);
        mRecyclerBanner.setData(mData)                              //设置初始化数据集
                .setTag("MainActivity")                             //用来控制生命周期的标记
                .setAdapter(mAdapter)                               //包含数据集的适配器
                .setIndicator(new int[]{R.mipmap.icon_dots_normal,  //指示器资源
                        R.mipmap.icon_dots_selected})
                .setIndicatorMargin(0,0,0,20);                      //指示器margin

        setData();
    }

    private void setData() {
        mData.clear();
        mData.add(R.mipmap.icon_raider_budget);
        mData.add(R.mipmap.icon_raider_carpentry);
        mData.add(R.mipmap.icon_raider_check);
        mData.add(R.mipmap.icon_raider_completed);
        mAdapter.notifyDataSetChanged();
    }

    //模拟请求数据后切换数据集
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mData.clear();
                mData.add(R.mipmap.icon_raider_furniture);
                mData.add(R.mipmap.icon_raider_house);
                mAdapter.notifyDataSetChanged();
                break;

            case R.id.button2:
                setData();
                break;
        }
    }

    private void sendBroadcast(int state, String tag) {
        Intent intent = new Intent();
        intent.setAction("timer");
        intent.putExtra("state", state);
        intent.putExtra("tag", tag);
        sendBroadcast(intent);
    }

    //在数据notify的时候会自动调用轮播
    //在onResume发广播通知它start主要是
    //程序回到桌面后回来的情况下让它继续轮播
    @Override
    protected void onResume() {
        sendBroadcast(2,MainActivity.class.getSimpleName());
        super.onResume();
    }

    @Override
    public void onPause() {
        sendBroadcast(0,MainActivity.class.getSimpleName());
        super.onPause();
    }

    @Override
    public void onDestroy() {
        sendBroadcast(1,MainActivity.class.getSimpleName());
        super.onDestroy();
    }
}
