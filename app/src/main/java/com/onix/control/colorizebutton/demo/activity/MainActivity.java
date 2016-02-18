package com.onix.control.colorizebutton.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.onix.control.colorizebutton.demo.adapter.HomePagerAdapter;
import com.onix.control.colorizebutton.demo.R;
import com.onix.control.colorizebutton.demo.fragment.HomeFragment;


public class MainActivity extends AppCompatActivity
        implements HomeFragment.SelectMenuListener {

    private ViewPager mPager;
    private Button mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (ViewPager) findViewById(R.id.pager);
        mBackBtn = (Button) findViewById(R.id.btn_back);
        HomePagerAdapter mAdapterPager = new HomePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapterPager);

        final Animation mAnimRedo = AnimationUtils.loadAnimation(this, R.anim.anim_redo_btn);
        Animation mAnimUndo = AnimationUtils.loadAnimation(this, R.anim.anim_undo_btn);
        mBackBtn.startAnimation(mAnimUndo);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(0);
                mBackBtn.startAnimation(mAnimRedo);
            }
        });
    }

    @Override
    public void onSelectMenuItem(int position) {
        // 0 - home default page
        if (position == 3) {
            startActivity(new Intent(this, PerformanceTestActivity.class));
        } else {
            mPager.setCurrentItem(position + 1);
        }
    }
}
