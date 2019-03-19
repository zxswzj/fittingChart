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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.database.DBHelper;
import com.example.fittingChart.module.Data;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.ui.TabLayout.MyFragmentPagerAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
    private TimePicker timePicker;
    Button btn_save, btn_cancel;

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
        timePicker = view.findViewById(R.id.fitting_timePicker);
        btn_save = view.findViewById(R.id.fitting_btn_save);
        btn_cancel = view.findViewById(R.id.fitting_btn_cancel);

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
                long lSeconds = (int)((SystemClock.elapsedRealtime() - baseTimer) / 1000);
                timer.cancel();

                long nHour = lSeconds / 3600;
                long nMin = lSeconds % 3600;
                long nSec = nMin % 60;
                nMin = nMin / 60;

                timePicker.setHour(nHour);
                timePicker.setMinute(nMin);

//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.fitting_fragment_container, fittingStopFragment);
//                ft.commit();
            }
        });

        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay,
                                      int minute) {
                Toast.makeText(getActivity(),
                        hourOfDay + "小时" + minute + "分钟",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = (Data)getActivity().getApplication();
                DBHelper db = new DBHelper(getContext());
                FittingData fd = new FittingData();
                fd.setNumber(new Random().nextInt(100));
                fd.setTime(new Date().getTime());
                db.addFittingItem(fd, "pushup");
                db.getAllFitting("pushup");
//                long aa = db.myquery();
                //boolean hastable = db.isTableExist("table_users");
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
