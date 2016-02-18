package com.onix.control.colorizebutton.demo.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onix.control.ColorizeButton;
import com.onix.control.colorizebutton.demo.R;


public class AllExampleFragment extends Fragment implements View.OnClickListener {

    private ColorizeButton mDefaultBtn, mWithBackgroundBtn, mWithoutBackgroundBtn, mWithScaletypeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgm_all_example,container,false);
        mDefaultBtn = (ColorizeButton)view.findViewById(R.id.default_btn);
        mWithBackgroundBtn = (ColorizeButton)view.findViewById(R.id.with_background_btn);
        mWithoutBackgroundBtn = (ColorizeButton)view.findViewById(R.id.without_background_btn);
        mWithScaletypeBtn = (ColorizeButton)view.findViewById(R.id.with_scaletype_btn);
        view.findViewById(R.id.colorOne).setOnClickListener(this);
        view.findViewById(R.id.colorTwo).setOnClickListener(this);;
        view.findViewById(R.id.colorThree).setOnClickListener(this);;
        view.findViewById(R.id.colorFour).setOnClickListener(this);;
        view.findViewById(R.id.colorFive).setOnClickListener(this);;
        view.findViewById(R.id.colorSix).setOnClickListener(this);
        view.findViewById(R.id.colorSeven).setOnClickListener(this);;
        view.findViewById(R.id.colorEach).setOnClickListener(this);;
        view.findViewById(R.id.colorNine).setOnClickListener(this);;
        view.findViewById(R.id.colorTen).setOnClickListener(this);;
        view.findViewById(R.id.colorEleven).setOnClickListener(this);;
        view.findViewById(R.id.colorTwelve).setOnClickListener(this);;

        return view;
    }


    @Override
    public void onClick(View v) {
        String color = v.getTag().toString();
        int paintColor = Color.parseColor(color);
        mDefaultBtn.setNormalColor(paintColor);
        mWithBackgroundBtn.setNormalColor(paintColor);
        mWithoutBackgroundBtn.setNormalColor(paintColor);
        mWithScaletypeBtn.setNormalColor(paintColor);
    }
}

