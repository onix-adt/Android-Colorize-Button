package com.onix.control.colorizebutton.demo.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.onix.control.colorizebutton.demo.fragment.AllExampleFragment;
import com.onix.control.colorizebutton.demo.fragment.ClickExampleFragment;
import com.onix.control.colorizebutton.demo.fragment.FocusedExampleFragment;
import com.onix.control.colorizebutton.demo.fragment.HomeFragment;


public class HomePagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 4;

    public HomePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ClickExampleFragment();
            case 2:
                return new FocusedExampleFragment();
            case 3:
                return new AllExampleFragment();
            default:
                return null;
        }
    }

}



