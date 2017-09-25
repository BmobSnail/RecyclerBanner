package com.snail.banner.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by snail
 * on 2017/9/18.
 * Todo 定时循环计时器
 */

public class CounterTimer {

    private static final String TAG = CounterTimer.class.getSimpleName();

    private static final int MSG_RUN = 1;
    private static final int MSG_PAUSE = 2;

    private long mInterval;

    private Context context;
    private OnTimerListener mTimerListener;
    private StateReceiver mReceiver;
    private String mTag;

    public CounterTimer(Context context,long mInterval) {
        this.context = context;
        this.mInterval = mInterval;

        mReceiver = new StateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("timer");
        context.registerReceiver(mReceiver, filter);
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public void setListener(OnTimerListener mTimerListener) {
        this.mTimerListener = mTimerListener;
    }

    private void destroy() {
        Log.i(TAG, "destroy-->");
        mHandler.removeMessages(MSG_RUN);
        mHandler.removeMessages(MSG_PAUSE);
        context.unregisterReceiver(mReceiver);
    }

    public void pause() {
        Log.i(TAG, "pause-->");
        mHandler.removeMessages(MSG_RUN);
        mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_PAUSE));
    }

    public void start() {
        Log.i(TAG, "start-->");
        mHandler.removeMessages(MSG_RUN);
        mHandler.removeMessages(MSG_PAUSE);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_RUN),mInterval);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            synchronized (CounterTimer.this) {
                switch (message.what) {
                    case MSG_RUN:
                        Log.i(TAG, "MSG_RUN");
                        mHandler.sendEmptyMessageDelayed(MSG_RUN,mInterval);
                        mTimerListener.onTick();
                        break;

                    case MSG_PAUSE:
                        break;
                }
            }
            return false;
        }
    });


    public interface OnTimerListener{
        void onTick();
    }

    private class StateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras()!= null&&
                    intent.getExtras().getString("tag").equals(mTag)) {
                switch (intent.getExtras().getInt("state")) {
                    case 0:
                        pause();
                        break;

                    case 1:
                        destroy();
                        break;

                    case 2:
                        start();
                        break;
                }
            }
        }
    }
}
