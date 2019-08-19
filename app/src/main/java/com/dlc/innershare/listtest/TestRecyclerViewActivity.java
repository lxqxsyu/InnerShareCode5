package com.dlc.innershare.listtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;

import com.dlc.innershare.R;
import com.dlc.innershare.adapter.TestRecyclerViewAdapter;
import com.dlc.innershare.base.BaseActivity;
import com.dlc.innershare.entry.ReciveData;
import com.google.gson.Gson;

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

public class TestRecyclerViewActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TestRecyclerViewAdapter mAdapter;
    private List<ReciveData.PhotoData> mPhotoDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recyclerview);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        new MyAsyncTask().execute("https://api.apiopen.top/getImages?page=0&count=40");

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TestRecyclerViewAdapter(this, mPhotoDatas);
        mRecyclerView.setAdapter(mAdapter);
    }


    class MyAsyncTask extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPhotoDatas.clear();
        }

        @Override
        protected Void doInBackground(String... strings) {
            requestData(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void integer) {
            super.onPostExecute(integer);
            mAdapter.notifyDataSetChanged();
        }
    }


    private void requestData(String urlstr){
        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            URL url = new URL(urlstr);
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
