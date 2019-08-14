package com.dlc.innershare.uithread;

import com.dlc.innershare.ToastUtil;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class TestPostDelayedActivity extends BaseOnUiThreadActivity{

    private static final int DELAY_TIME = 3; //延迟时间

    @Override
    public void startCount() {
        mShowTime.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast("线程启动后延迟 " + DELAY_TIME + " 秒后我才执行");
            }
        }, DELAY_TIME * 1000);
    }

    @Override
    public void updateCount(final int count) {
        mShowTime.post(new Runnable() {
            @Override
            public void run() {
                mShowTime.setText(String.format("正在计数：%d", count));
            }
        });
    }

    @Override
    public void stopCount() {
        mShowTime.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast("线程已结束后延迟 " + DELAY_TIME + " 秒我才执行");
            }
        }, DELAY_TIME * 1000);
    }
}
