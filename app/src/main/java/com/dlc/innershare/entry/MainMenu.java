package com.dlc.innershare.entry;

import com.dlc.innershare.base.BaseActivity;

/**
 * 描述：
 * 日期：2019/8/16
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class MainMenu {

    public String menuName;
    public Class<? extends BaseActivity> turnToClass;

    public MainMenu(String menuName, Class<? extends BaseActivity> turnToClass) {
        this.menuName = menuName;
        this.turnToClass = turnToClass;
    }
}
