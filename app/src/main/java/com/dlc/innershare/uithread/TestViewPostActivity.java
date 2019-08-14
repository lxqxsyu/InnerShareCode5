package com.dlc.innershare.uithread;

import com.dlc.innershare.ToastUtil;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class TestViewPostActivity extends BaseOnUiThreadActivity {

    @Override
    public void startCount() {
        mShowTime.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast("线程已经启动");
            }
        });
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
        mShowTime.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast("线程已结束");
            }
        });
    }
}
