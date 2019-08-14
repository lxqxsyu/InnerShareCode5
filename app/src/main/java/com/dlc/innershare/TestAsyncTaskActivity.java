package com.dlc.innershare;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dlc.innershare.base.BaseActivity;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class TestAsyncTaskActivity extends BaseActivity implements CountInterface {

    protected TextView mShowTime;
    private MyTask mMyTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread_create);
        mShowTime = findViewById(R.id.tv_show_time);
    }

    private class MyTask extends AsyncTask<Void, Integer, Boolean>{

        private int mCurrentTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCurrentTime = 0;
            updateCount(mCurrentTime);
            startCount();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            while(!isCancelled()){
                try {
                    Thread.sleep(CountInterface.SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mCurrentTime++;
                publishProgress(mCurrentTime);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            updateCount(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            stopCount();
        }
    }

    /**
     * 启动线程 按钮绑定事件回调
     * @param view
     */
    public void clickStartThread(View view) {
        mMyTask = new MyTask();
        mMyTask.execute();
    }

    /**
     * 结束线程 按钮绑定事件回调
     * @param view
     */
    public void clickStopThread(View view) {
       if(mMyTask == null) return;
       mMyTask.cancel(true);
       stopCount();
    }

    @Override
    public void startCount() {
        ToastUtil.showToast("线程已经启动");
    }

    @Override
    public void updateCount(final int count) {
        mShowTime.setText(String.format("正在计数：%d", count));
    }

    @Override
    public void stopCount() {
        ToastUtil.showToast("线程已结束");
    }
}
