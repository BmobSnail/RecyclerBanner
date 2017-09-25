package com.snail.recyclerbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                .setOrientation(RecyclerBanner.Orientation.RIGHT)   //指示器所在的位置
                .setIndicator(new int[]{R.mipmap.icon_dots_normal,  //指示器资源
                        R.mipmap.icon_dots_selected})
                .setAdapter(mAdapter);                              //包含数据集的适配器

        setData();
    }

    private void setData() {
        mData.clear();
        mData.add(R.mipmap.icon_raider_budget);
        mData.add(R.mipmap.icon_raider_carpentry);
        mData.add(R.mipmap.icon_raider_check);
        mData.add(R.mipmap.icon_raider_completed);
        mAdapter.notifyDataSetChanged();
        mRecyclerBanner.setIndicatorMargin(10,10,10,10);
        mRecyclerBanner.setIndicatorOrientation(RecyclerBanner.Orientation.LEFT);
    }

    //模拟请求数据后切换数据集
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mData.clear();
                mData.add(R.mipmap.icon_raider_furniture);
//                mData.add(R.mipmap.icon_raider_house);
                mAdapter.notifyDataSetChanged();
                mRecyclerBanner.setIndicatorMargin(30,30,30,30);
                mRecyclerBanner.setIndicatorOrientation(RecyclerBanner.Orientation.RIGHT);
                break;

            case R.id.button2:
                setData();
                break;
        }
    }

    @Override
    protected void onPause() {
        mRecyclerBanner.onPause(); //实现生命周期可以管理轮播，暂停
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mRecyclerBanner.onDestroy();//同理，销毁（实现生命周期可以有效管理资源）
        super.onDestroy();
    }
}
