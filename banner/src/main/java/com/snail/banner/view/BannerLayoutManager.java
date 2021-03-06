package com.snail.banner.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * Created by snail
 * on 2017/9/19.
 * Todo 布局管理器(控制滚动速度)
 */

public class BannerLayoutManager extends LinearLayoutManager {

    private static float DURATION = 100f;

    public BannerLayoutManager(Context context) {
        this(context, LinearLayoutManager.HORIZONTAL, false);
    }

    public BannerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller scroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return DURATION / displayMetrics.densityDpi;
            }
        };
        scroller.setTargetPosition(position);
        startSmoothScroll(scroller);
    }
}
