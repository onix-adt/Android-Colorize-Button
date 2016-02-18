package com.onix.control.colorizebutton.demo.adapter;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.onix.control.ColorizeButton;
import com.onix.control.DrawableNotSupportedException;
import com.onix.control.colorizebutton.demo.R;

import java.util.List;


public class PerformanceAdapter extends RecyclerView.Adapter<PerformanceAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mCells;
    private int mPositionCheckButton;

    public PerformanceAdapter(Context context, List<String> cells) {
        this.mContext = context;
        this.mCells = cells;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ColorizeButton mColorizeButton;
        public ViewHolder(ColorizeButton v) {
            super(v);
            mColorizeButton = v;
        }
    }

    @Override
    public PerformanceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        ColorizeButton mColorizeButton = new ColorizeButton(mContext);
        ViewHolder vh = new ViewHolder(mColorizeButton);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            holder.mColorizeButton.setColorizedImage(mImgIds[position]);
            holder.mColorizeButton.setBackgroundColor(Color.TRANSPARENT);
            holder.mColorizeButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            } catch (DrawableNotSupportedException e) {
                e.printStackTrace();
            }
        if (position == mPositionCheckButton) {
            holder.mColorizeButton.setNormalColor(setColor(mColorIds[mPositionCheckButton]));
        }

    }

    @Override
    public int getItemCount() {
        return mCells.size();
    }

    public void setPositionButton(int positionCheckButton){
        mPositionCheckButton = positionCheckButton;
        notifyDataSetChanged();

    }

    private int setColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mContext.getResources().getColor(color, mContext.getTheme());
        }else {
            return mContext.getResources().getColor(color);
        }
    }

    private Integer[] mImgIds = {R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_cat_white_48dp, R.drawable.ic_cat_white_48dp, R.drawable.ic_airballoon_white_48dp,
            R.drawable.ic_airballoon_white_48dp, R.drawable.ic_apple_mobileme, R.drawable.ic_apple_mobileme,
            R.drawable.ic_motorbike_white_48dp, R.drawable.ic_motorbike_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_apple_mobileme, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_airballoon_white_48dp, R.drawable.ic_cat_white_48dp, R.drawable.ic_airballoon_white_48dp,
            R.drawable.ic_airballoon_white_48dp, R.drawable.ic_guitar_white_48dp, R.drawable.ic_cat_white_48dp,
            R.drawable.ic_apple_mobileme, R.drawable.ic_apple_mobileme,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp, R.drawable.ic_cat_white_48dp,
            R.drawable.ic_apple_mobileme, R.drawable.ic_airballoon_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_cat_white_48dp, R.drawable.ic_apple_mobileme, R.drawable.ic_cat_white_48dp,
            R.drawable.ic_cat_white_48dp, R.drawable.ic_cat_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_apple_mobileme, R.drawable.ic_cat_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_cat_white_48dp, R.drawable.ic_motorbike_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_apple_mobileme,
            R.drawable.ic_cat_white_48dp, R.drawable.ic_motorbike_white_48dp, R.drawable.ic_motorbike_white_48dp,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_apple_mobileme, R.drawable.ic_airballoon_white_48dp,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp, R.drawable.ic_guitar_white_48dp,
            R.drawable.ic_guitar_white_48dp};

    private Integer[] mColorIds = {R.color.green, R.color.teal,
            R.color.light_green, R.color.blue_grey, R.color.colorPrimary,
            R.color.yellow, R.color.pink, R.color.colorAccent,
            R.color.light_green, R.color.green, R.color.teal,
            R.color.light_green, R.color.blue_grey, R.color.colorPrimary,
            R.color.yellow, R.color.pink, R.color.colorAccent,
            R.color.light_green, R.color.green, R.color.teal,
            R.color.light_green, R.color.blue_grey, R.color.colorPrimary,
            R.color.yellow, R.color.pink, R.color.colorAccent,
            R.color.light_green, R.color.green, R.color.teal,
            R.color.light_green, R.color.blue_grey, R.color.colorPrimary,
            R.color.yellow, R.color.pink, R.color.colorAccent,
            R.color.light_green, R.color.green, R.color.teal,
            R.color.light_green, R.color.blue_grey, R.color.colorPrimary,
            R.color.yellow, R.color.pink, R.color.colorAccent,
            R.color.light_green, R.color.green
    };

}
