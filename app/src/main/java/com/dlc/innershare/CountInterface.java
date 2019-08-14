package com.dlc.innershare;

/**
 * 描述：
 * 日期：2019/8/14
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public interface CountInterface {

    int SLEEP_TIME = 1000; //休眠1000ms

    /**
     * 开始计数
     */
    void startCount();

    /**
     * 更新计数
     * @param count
     */
    void updateCount(int count);

    /**
     * 结束计数
     */
    void stopCount();
}
