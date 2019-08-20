package com.dlc.innershare.listtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.widget.GridView;
import android.widget.ListView;

import com.dlc.innershare.R;
import com.dlc.innershare.adapter.TestListAdapter;
import com.dlc.innershare.base.BaseActivity;
import com.dlc.innershare.entry.ReciveData;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class TestListViewActivity extends BaseActivity {

    private GridView mListView;
    private TestListAdapter mAdapter;
    private List<ReciveData.PhotoData> mPhotoDatas = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_listview);


        mListView = findViewById(R.id.test_listview);
        mAdapter = new TestListAdapter(mPhotoDatas, getScreenWidth());
        mListView.setAdapter(mAdapter);

        initData();
    }


    private void initData(){
        new Thread(){
            @Override
            public void run() {
                android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                requestData();
            }
        }.start();
    }


    private void requestData(){
        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            URL url = new URL("https://api.apiopen.top/getImages?page=0&count=40");
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000); //连接超时
            connection.setReadTimeout(3000); //读取超时
            connection.setRequestMethod("POST"); //POST 请求方式
            connection.setDoInput(true); //默认true
            connection.setDoOutput(false); //默认 false
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode != HttpsURLConnection.HTTP_OK){
                throw new IOException("HTTP error code : " + responseCode);
            }
            String result = getStringByStream(connection.getInputStream());
            if(result == null){
                throw new IOException("读取流失败");
            }
            Log.d("TEST", "result = " + result);
            ReciveData reciveData = new Gson().fromJson(result, ReciveData.class);
            mPhotoDatas.addAll(reciveData.result);

            mListView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringByStream(InputStream inputStream){
        Reader reader;
        try {
            reader = new InputStreamReader(inputStream, "UTF-8");
            char[] rawBuffer = new char[512];
            StringBuffer buffer = new StringBuffer();
            int length;
            while((length = reader.read(rawBuffer)) != -1){
                buffer.append(rawBuffer, 0, length);
            }
            return buffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getScreenWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        return width;
    }
}
