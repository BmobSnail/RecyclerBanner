package com.snail.recyclerbanner.adapter.banner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.snail.recyclerbanner.R;

/**
 * Created by snail
 * on 2017/9/18.
 * Todo 广告item持有者holder
 */

public class BannerHolder extends RecyclerView.ViewHolder {

    private ImageView mImage;

    public BannerHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.item_banner_image);
    }

    public void bindData(int res){
        Glide.with(mImage.getContext()).load(res).diskCacheStrategy(DiskCacheStrategy.RESULT).into(mImage);
    }
}
