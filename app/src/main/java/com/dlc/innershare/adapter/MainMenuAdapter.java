package com.dlc.innershare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.dlc.innershare.R;
import com.dlc.innershare.entry.MainMenu;

import java.util.List;

/**
 * 描述：
 * 日期：2019/8/16
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class MainMenuAdapter extends BaseAdapter {

    private List<MainMenu> mMenusData;

    public MainMenuAdapter(List<MainMenu> menus){
        mMenusData = menus;
    }
    @Override
    public int getCount() {
        if(mMenusData == null) return 0;
        return mMenusData.size();
    }

    @Override
    public MainMenu getItem(int position) {
        return mMenusData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_main_button, null, true);
            viewHolder.turnButton = convertView.findViewById(R.id.btn_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MainMenu menu = getItem(position);
        viewHolder.turnButton.setText(menu.menuName);
        return convertView;
    }

    static class ViewHolder{
        Button turnButton;
    }
}
