package com.dlc.innershare.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dlc.innershare.CountInterface;
import com.dlc.innershare.base.BaseActivity;
import com.dlc.innershare.R;
import com.dlc.innershare.ToastUtil;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public abstract class BaseCreateThreadActivity extends BaseActivity implements CountInterface {


    protected static final int HAND_UPDATE_TIME = 0x0001; //计数更新消息
    protected static final int HAND_START_THREAD = 0x0002; //线程启动消息
    protected static final int HAND_STOP_THREAD = 0x0003;  //线程结束消息

    protected TextView mShowTime;

    private Thread mCountTimeThread;
    private int mCurrentTime;
    private boolean isRunning;

    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){  //处理消息
                case HAND_UPDATE_TIME:
                    updateCount(mCurrentTime);
                    break;
                case HAND_STOP_THREAD:
                    stopCount();
                    break;
                case HAND_START_THREAD:
                    startCount();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread_create);
        mShowTime = findViewById(R.id.tv_show_time);
    }

    protected abstract Thread createThread();

    protected void runCount(){
        mHandler.sendEmptyMessage(HAND_START_THREAD);
        while (isRunning){
            try {
                Thread.sleep(CountInterface.SLEEP_TIME); //休息 1 秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mCurrentTime++;
            mHandler.sendEmptyMessage(HAND_UPDATE_TIME);
        }
        mHandler.sendEmptyMessage(HAND_STOP_THREAD);
    }

    /**
     * 启动线程 按钮绑定事件回调
     * @param view
     */
    public void clickStartThread(View view) {
        startCount();
    }

    /**
     * 结束线程 按钮绑定事件回调
     * @param view
     */
    public void clickStopThread(View view) {
        stopCount();
    }

    /**
     * 启动一个线程
     */
    private void startThread(){
        stopCount();
        isRunning = true;
        mCountTimeThread = createThread();
        mCurrentTime = 0;
        mHandler.sendEmptyMessage(HAND_UPDATE_TIME);
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
