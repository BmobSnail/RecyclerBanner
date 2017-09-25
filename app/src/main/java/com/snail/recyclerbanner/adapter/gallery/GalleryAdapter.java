package com.snail.recyclerbanner.adapter.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snail.banner.utils.HolderUtils;
import com.snail.recyclerbanner.R;

import java.util.List;

/**
 * Created by snail
 * on 2017/9/25.
 * Todo
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> {

    private List<Integer> mData;
    private HolderUtils mHolderUtils;

    public GalleryAdapter(List<Integer> mData) {
        this.mData = mData;
        mHolderUtils = new HolderUtils();
    }

    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_gallery,parent,false);
        mHolderUtils.onCreateViewHolder(parent,view);
        return new GalleryHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryHolder holder, int position) {
        mHolderUtils.onBindViewHolder(holder.itemView,position,getItemCount());
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
