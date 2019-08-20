package com.dlc.innershare.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlc.innershare.R;
import com.dlc.innershare.entry.ReciveData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.MyViewHolder> {

    private List<ReciveData.PhotoData> mDatas;

    private Context mContext;

    public TestRecyclerViewAdapter(Context context, List<ReciveData.PhotoData> datas){
        mDatas = datas;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_test, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        ReciveData.PhotoData itemData = mDatas.get(position);
        myViewHolder.textView.setText(itemData.time);
        Glide.with(mContext).load(itemData.img).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if(mDatas == null) return 0;
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imageview);
            textView = itemView.findViewById(R.id.item_textview);
        }
    }
}
