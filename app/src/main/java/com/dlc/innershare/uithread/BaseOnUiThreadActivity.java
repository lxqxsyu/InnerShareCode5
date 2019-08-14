package com.dlc.innershare.uithread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dlc.innershare.CountInterface;
import com.dlc.innershare.base.BaseActivity;
import com.dlc.innershare.R;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public abstract class BaseOnUiThreadActivity extends BaseActivity implements CountInterface {

    protected TextView mShowTime;

    private Thread mCountTimeThread;
    private int mCurrentTime;
    private boolean isRunning;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread_create);
        mShowTime = findViewById(R.id.tv_show_time);
    }

    private void createThread(){
        mCountTimeThread = new Thread(){
            @Override
            public void run() {
                startCount();
                while (isRunning){
                    try {
                        Thread.sleep(CountInterface.SLEEP_TIME); //休息 1 秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurrentTime++; //计数加 1
                    updateCount(mCurrentTime);
                }
                stopCount();
            }
        };
    }

    /**
     * 启动线程 按钮绑定事件回调
     * @param view
     */
    public void clickStartThread(View view) {
        startThread();
    }

    /**
     * 结束线程 按钮绑定事件回调
     * @param view
     */
    public void clickStopThread(View view) {
        stopThread();
    }

    /**
     * 启动一个线程
     */
    private void startThread(){
        stopThread();
        isRunning = true;
        createThread();
        mCurrentTime = 0;
        updateCount(mCurrentTime);
        mCountTimeThread.start();
    }

    /**
     * 结束一个线程
     */
    private void stopThread(){
        if(mCountTimeThread == null) return;
        isRunning = false;
        mCountTimeThread = null;
    }

}
