package com.dlc.innershare.thread;

import com.dlc.innershare.ToastUtil;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class TestThreadCreateActivity extends BaseCreateThreadActivity {

    @Override
    protected Thread createThread() {
        return new Thread(){
            @Override
            public void run() {
                runCount();
            }
        };
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
