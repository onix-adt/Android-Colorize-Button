package com.onix.control.colorizebutton.demo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.onix.control.colorizebutton.demo.R;


public class HomeListAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mMenuItems;

    public HomeListAdapter(Context context, String[] objects) {
        mContext = context;
        mMenuItems = objects;
    }

    static class ViewHolder {
        TextView mTxtItem;
    }

    @Override
    public String getItem(int i) {
        return mMenuItems[i];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_home_menu, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.mTxtItem = (TextView) convertView.findViewById(R.id.txt_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTxtItem.setText(getItem(position));

        return convertView;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return mMenuItems.length;
    }
}
