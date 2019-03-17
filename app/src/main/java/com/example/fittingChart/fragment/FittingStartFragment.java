package com.example.fittingChart.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fittingChart.R;
import com.example.fittingChart.ui.TabLayout.MyFragmentPagerAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class FittingStartFragment extends Fragment {

    String TAG = "Fragment";
    FittingStopFragment fittingStopFragment;
    TextView tv;
    long baseTimer;
    Timer timer;

    Button bt_start, bt_stop;

    View view;
    final Handler startTimehandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (null != tv) {
                tv.setText((String) msg.obj);
            }
        }
    };

//    public FittingStartFragment() {
//        // Required empty public constructor
//        Log.i(TAG, "FittingStartFragment.FittingStartFragment");
//
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "FittingStartFragment.onCreate");
        fittingStopFragment = new FittingStopFragment();
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Log.i(TAG, "FittingStartFragment.onCreateView");
//
//    view = inflater.inflate(R.layout.fragment_fitting, container, false);        Button bt_start,bt_stop;


//        return view;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Fragment", "FittingStartFragment.onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fitting_start, container, false);
        timer = new Timer();
        tv = view.findViewById(R.id.fitting_timerView);
        bt_start = view.findViewById(R.id.fitting_btn_start);
        bt_stop = view.findViewById(R.id.fitting_btn_stop);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Fragment", "FittingStartFragment.bt_start.onClick");

                baseTimer = (int) SystemClock.elapsedRealtime();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        int time = (int)((SystemClock.elapsedRealtime() - baseTimer) / 1000);
                        String hh = new DecimalFormat("00").format(time / 3600);
                        String mm = new DecimalFormat("00").format(time % 3600 / 60);
                        String ss = new DecimalFormat("00").format(time % 60);
                        String timeFormat = new String(hh + ":" + mm + ":" + ss);
                        Message msg = new Message();
                        msg.obj = timeFormat;
                        startTimehandler.sendMessage(msg);
                    }
                }, 0, 1000L);
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Fragment", "FittingStartFragment.bt_stop.onClick");
//                int time = (int)((SystemClock.elapsedRealtime() - baseTimer) / 1000);
//                String hh = new DecimalFormat("00").format(time / 3600);
//                String mm = new DecimalFormat("00").format(time % 3600 / 60);
//                String ss = new DecimalFormat("00").format(time % 60);
//                String timeFormat = new String(hh + ":" + mm + ":" + ss);
//                Message msg = new Message();
//                msg.obj = timeFormat;
//                startTimehandler.sendMessage(msg);
//                timer.cancel();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fitting_fragment_container, fittingStopFragment);
                ft.commit();

                //getFragmentManager().beginTransaction().add(R.id.content, fittingStopFragment).addToBackStack(null).commit();

            }
        });

        return view;
    }

}
