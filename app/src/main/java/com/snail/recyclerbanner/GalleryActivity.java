package com.snail.recyclerbanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.snail.banner.view.GallerySnapHelper;
import com.snail.recyclerbanner.adapter.gallery.GalleryAdapter;
import com.snail.recyclerbanner.utils.BlurBitmapUtils;
import com.snail.recyclerbanner.utils.ViewSwitchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snail
 * on 2017/9/25.
 * Todo 画廊页面
 */

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageView mBlurView;
    private GallerySnapHelper mSnapHelper;
    private Runnable mBlurRunnable;

    private List<Integer> mData;
    private int last = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerGallery);
        mBlurView = (ImageView) findViewById(R.id.blur);

        mData = new ArrayList<>();
        mData.add(R.mipmap.icon_baby1);
        mData.add(R.mipmap.icon_baby2);
        mData.add(R.mipmap.icon_baby3);
        mData.add(R.mipmap.icon_baby4);
        mData.add(R.mipmap.icon_baby5);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new GalleryAdapter(mData));

        mSnapHelper = new GallerySnapHelper();
        mSnapHelper.setCurrentItemPos(2);
        mSnapHelper.attachToRecyclerView(mRecyclerView);

        //添加高斯模糊
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBlur();
                }
            }
        });
    }

    private void notifyBlur() {
        if (last == mSnapHelper.getCurrentItemPos()) return;
        last = mSnapHelper.getCurrentItemPos();
        final int resId = mData.get(mSnapHelper.getCurrentItemPos());
        mBlurView.removeCallbacks(mBlurRunnable);
        mBlurRunnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
                ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView, BlurBitmapUtils.getBlurBitmap(mBlurView.getContext(), bitmap, 15));
            }
        };
        mBlurView.postDelayed(mBlurRunnable, 300);
    }
}
