package com.dlc.innershare.uithread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dlc.innershare.ToastUtil;
import com.dlc.innershare.uithread.BaseOnUiThreadActivity;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class TestRunOnUiThreadActivity extends BaseOnUiThreadActivity {

    @Override
    public void startCount() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast("线程已经启动");
            }
        });
    }

    @Override
    public void updateCount(final int count) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mShowTime.setText(String.format("正在计数：%d", count));
            }
        });
    }

    @Override
    public void stopCount() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast("线程已结束");
            }
        });
    }
}
