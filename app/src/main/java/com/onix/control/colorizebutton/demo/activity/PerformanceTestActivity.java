package com.onix.control.colorizebutton.demo.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.onix.control.colorizebutton.demo.adapter.PerformanceAdapter;
import com.onix.control.colorizebutton.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class PerformanceTestActivity extends AppCompatActivity {
    private static int COUNT_ITEM = 42;
    private PerformanceAdapter mAdapter;
    private int mPositionChangedButton;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfomance);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.grid_view);
        Button mBackBtn = (Button) findViewById(R.id.btn_back);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initButtonList();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        ArrayList mItems = initButtonList();
        mAdapter = new PerformanceAdapter(getApplicationContext(), mItems);
        mRecyclerView.setAdapter(mAdapter);

        // create mTimer for clicks
        startTimer();
    }

    private void startTimer() {
        mTimer = new Timer();
        mTimerTask = new TimerTask();
        mTimer.schedule(mTimerTask, 1000, 200);
    }

    private void stopTimer() {
        mTimerTask.cancel();
        mTimer.purge();
        mTimer.cancel();
    }

    private ArrayList initButtonList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < COUNT_ITEM; i++) {
            list.add(i, "");
        }
        return list;
    }

    public int randInt(int min, int max) {
        return min + (int) (Math.random() * max);
    }

    public class TimerTask extends java.util.TimerTask {

        @Override
        public void run() {

            mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mPositionChangedButton = randInt(0, COUNT_ITEM);
                    mAdapter.setPositionButton(mPositionChangedButton);
                }
            });

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
        stopTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}
