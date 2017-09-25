package com.snail.recyclerbanner.adapter.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.snail.recyclerbanner.R;

/**
 * Created by snail
 * on 2017/9/25.
 * Todo
 */

public class GalleryHolder extends RecyclerView.ViewHolder {

    private ImageView mImage;

    public GalleryHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.imageView);
    }

    public void bindData(int res) {
        mImage.setImageResource(res);
    }
}
