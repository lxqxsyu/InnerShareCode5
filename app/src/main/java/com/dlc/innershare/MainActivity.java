package com.dlc.innershare;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dlc.innershare.adapter.MainMenuAdapter;
import com.dlc.innershare.base.BaseActivity;
import com.dlc.innershare.entry.MainMenu;
import com.dlc.innershare.thread.TestRunnableCreateActivity;
import com.dlc.innershare.thread.TestThreadCreateActivity;
import com.dlc.innershare.uithread.TestPostDelayedActivity;
import com.dlc.innershare.uithread.TestRunOnUiThreadActivity;
import com.dlc.innershare.uithread.TestViewPostActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView mListView;
    private MainMenuAdapter mAdapter;
    private List<MainMenu> mButtonsData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.main_button_list);
        initButtonsData();
        mAdapter = new MainMenuAdapter(mButtonsData);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                turnTo(mAdapter.getItem(position).turnToClass);
            }
        });
    }

    private void initButtonsData(){
        mButtonsData.add(new MainMenu("Thread 创建线程", TestThreadCreateActivity.class));
        mButtonsData.add(new MainMenu("Runnable 创建线程", TestRunnableCreateActivity.class));
        mButtonsData.add(new MainMenu("runOnUiThread 切换到 UI 线程", TestRunOnUiThreadActivity.class));
        mButtonsData.add(new MainMenu("View.post(Runnable) 切换到 UI 线程", TestViewPostActivity.class));
        mButtonsData.add(new MainMenu("postDelayed 切换到 UI 线程", TestPostDelayedActivity.class));
        mButtonsData.add(new MainMenu("AsyncTask 使用", TestAsyncTaskActivity.class));
    }
}
