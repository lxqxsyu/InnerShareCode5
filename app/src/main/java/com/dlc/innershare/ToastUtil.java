package com.dlc.innershare;

import android.widget.Toast;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class ToastUtil {

    public static void showToast(String msg){
        Toast.makeText(App.mInstance.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
