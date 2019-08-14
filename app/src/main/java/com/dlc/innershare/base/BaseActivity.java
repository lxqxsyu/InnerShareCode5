package com.dlc.innershare.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected void turnTo(Class<? extends BaseActivity> toClass){
        Intent intent = new Intent();
        intent.setClass(this, toClass);
        startActivity(intent);
    }
}
