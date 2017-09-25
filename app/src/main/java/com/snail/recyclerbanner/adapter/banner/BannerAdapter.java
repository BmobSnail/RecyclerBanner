package com.snail.recyclerbanner.adapter.banner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snail.recyclerbanner.R;

import java.util.List;

/**
 * Created by snail
 * on 2017/9/18.
 * Todo 广告内容适配器
 */

public class BannerAdapter extends RecyclerView.Adapter<BannerHolder> {

    private List<Integer> mData;

    public BannerAdapter(List<Integer> mData) {
        this.mData = mData;
    }

    @Override
    public BannerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.item_banner,parent,false);
        return new BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(BannerHolder holder, int position) {

        //一定要对数据集的size取余，否则会越界异常
        holder.bindData(mData.get(position%mData.size()));
    }

    //自己写的广告栏的适配器，如果多余2条就无限轮播，一定要使用MAX_VALUE
    //不需要担心OOM，溢出那些，recyclerView的重用机制已经足够完美了
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() < 2 ? mData.size() : Integer.MAX_VALUE;
    }
}
