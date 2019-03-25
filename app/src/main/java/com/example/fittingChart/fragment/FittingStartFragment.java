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
import android.widget.EditText;
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

    String tableName,tableDBName;
    String TAG = "Fragment";
    FittingStopFragment fittingStopFragment;
    long baseTimer;
    Timer timer;
    EditText et_num,et_duration;
    Button bt_start, bt_stop;
    private TimePicker timePicker;
    Button btn_save, btn_cancel;

    long lHour,lMinute,lSecond;

    View view;
    final Handler startTimehandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (null != et_duration) {
                et_duration.setText((String) msg.obj);
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
//        fittingStopFragment = new FittingStopFragment();
        Bundle bundle = getArguments();
        tableName = bundle.getString("tableName");
        tableDBName = bundle.getString("tableDBName");
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
        bt_start = view.findViewById(R.id.fitting_btn_start);
        bt_stop = view.findViewById(R.id.fitting_btn_stop);
        timePicker = view.findViewById(R.id.fitting_timePicker);
        btn_save = view.findViewById(R.id.fitting_btn_save);
        btn_cancel = view.findViewById(R.id.fitting_btn_cancel);
        et_num = view.findViewById(R.id.fitting_pt_num);
        et_duration = view.findViewById(R.id.fitting_pt_duration);

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
                lSecond = (int)((SystemClock.elapsedRealtime() - baseTimer) / 1000);
                timer.cancel();

                String hh = new DecimalFormat("00").format(lSecond / 3600);
                String mm = new DecimalFormat("00").format(lSecond % 3600 / 60);
                String ss = new DecimalFormat("00").format(lSecond % 60);
                String timeFormat = new String(hh + ":" + mm + ":" + ss);

                et_duration.setText(timeFormat);
                //timePicker.setHour(nHour);
                //timePicker.setMinute(nMin);
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
                db.openDatabase();
                FittingData fd = new FittingData();
                fd.setNumber(Integer.parseInt(et_num.getEditableText().toString().trim()));
                fd.setDurationTime(lSecond);
                fd.setLocalTime(new Date().getTime());
                fd.setDes("aaa");

                //db.addFittingItem(tableDBName, fd);
                db.updateShowTableItem(tableDBName);
                db.closeDatabase();
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
