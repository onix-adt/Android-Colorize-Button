package com.onix.control.colorizebutton.demo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onix.control.ColorizeButton;
import com.onix.control.colorizebutton.demo.R;


public class FocusedExampleFragment extends Fragment implements View.OnClickListener {

    private ColorizeButton mFocusedBtnLeft;
    private ColorizeButton mFocusedBtnCenter;
    private ColorizeButton mFocusedBtnRight;
    private int mPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgm_focus_example, container, false);
        mFocusedBtnLeft = (ColorizeButton) view.findViewById(R.id.default_btn);
        mFocusedBtnCenter = (ColorizeButton) view.findViewById(R.id.with_background_btn);
        mFocusedBtnRight = (ColorizeButton) view.findViewById(R.id.without_background_btn);
        view.findViewById(R.id.btn_prev).setOnClickListener(this);
        view.findViewById(R.id.btn_redo).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prev:
                prevPage();
                break;
            case R.id.btn_redo:
                nextPage();
                break;
        }
    }

    private void nextPage() {
        mPosition++;
        if (mPosition == 3) {
            mPosition = 0;
        }
        focus(mPosition);
    }

    private void prevPage() {
        mPosition--;
        if (mPosition == -1) {
            mPosition = 2;
        }
        focus(mPosition);
    }

    private void focus(int position) {
        switch (position) {
            case 0:
                mFocusedBtnLeft.setFocusable(true);
                mFocusedBtnLeft.requestFocusFromTouch();
                mFocusedBtnCenter.clearFocus();
                mFocusedBtnRight.clearFocus();

                break;
            case 1:
                mFocusedBtnCenter.setFocusable(true);
                mFocusedBtnCenter.requestFocusFromTouch();
                mFocusedBtnLeft.clearFocus();
                mFocusedBtnRight.clearFocus();

                break;
            case 2:
                mFocusedBtnRight.setFocusable(true);
                mFocusedBtnRight.requestFocusFromTouch();
                mFocusedBtnCenter.clearFocus();
                mFocusedBtnLeft.clearFocus();
                break;
        }
    }
}