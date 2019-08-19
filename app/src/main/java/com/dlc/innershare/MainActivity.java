package com.dlc.innershare;

import android.os.Bundle;
import android.view.View;

import com.dlc.innershare.adapter.TestRecyclerViewAdapter;
import com.dlc.innershare.base.BaseActivity;
import com.dlc.innershare.listtest.TestListViewActivity;
import com.dlc.innershare.listtest.TestRecyclerViewActivity;
import com.dlc.innershare.thread.TestRunnableCreateActivity;
import com.dlc.innershare.thread.TestThreadCreateActivity;
import com.dlc.innershare.uithread.TestPostDelayedActivity;
import com.dlc.innershare.uithread.TestRunOnUiThreadActivity;
import com.dlc.innershare.uithread.TestViewPostActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testThreadCreate(View view) {
        turnTo(TestThreadCreateActivity.class);
    }

    public void testRunnableCreate(View view) {
        turnTo(TestRunnableCreateActivity.class);
    }

    public void testRunOnUiThread(View view) {
        turnTo(TestRunOnUiThreadActivity.class);
    }

    public void testViewPost(View view) {
        turnTo(TestViewPostActivity.class);
    }

    public void testPostDelayed(View view) {
        turnTo(TestPostDelayedActivity.class);
    }

    public void testAsyncTask(View view) {
        turnTo(TestAsyncTaskActivity.class);
    }

    public void testListAdapter(View view) {
        turnTo(TestListViewActivity.class);
    }

    public void testRecyclerView(View view) {
        turnTo(TestRecyclerViewActivity.class);
    }
}
