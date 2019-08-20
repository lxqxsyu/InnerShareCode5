package com.dlc.innershare.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlc.innershare.R;
import com.dlc.innershare.entry.ReciveData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class TestListAdapter extends BaseAdapter {

    private List<ReciveData.PhotoData> mDatas;
    private int mScreenWidth;

    public TestListAdapter(List<ReciveData.PhotoData> datas, int screenWidth){
        mDatas = datas;
        mScreenWidth = screenWidth;
    }

    @Override
    public int getCount() {
        if(mDatas == null) return 0;
        return mDatas.size();
    }

    @Override
    public ReciveData.PhotoData getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_test, null, true);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.item_imageview);
            holder.textView = convertView.findViewById(R.id.item_textview);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(getItem(position).time);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher_background)
                .showImageForEmptyUri(R.drawable.ic_launcher_background) // resource or drawable
                .showImageOnFail(R.drawable.ic_launcher_background) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .build();
        ImageLoader.getInstance().displayImage(getItem(position).img, holder.imageView,options);
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
