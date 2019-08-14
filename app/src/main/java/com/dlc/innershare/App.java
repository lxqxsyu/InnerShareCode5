package com.dlc.innershare;

import android.app.Application;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class App extends Application {

    public static App mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
