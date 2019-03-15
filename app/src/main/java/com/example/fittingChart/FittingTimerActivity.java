package com.example.fittingChart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class FittingTimerActivity extends AppCompatActivity {

    TextView tv;
    long baseTimer;
    Timer timer;
    final Handler startTimehandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (null != tv) {
                tv.setText((String) msg.obj);
            }
        }
    };


    public FittingTimerActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitting_timer);
        Button bt_start,bt_stop;
        timer = new Timer();

        tv = findViewById(R.id.fitting_timerView);
        bt_start = findViewById(R.id.fitting_btn_start);
        bt_stop = findViewById(R.id.fitting_btn_stop);

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseTimer = (int)SystemClock.elapsedRealtime();
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
                int time = (int)((SystemClock.elapsedRealtime() - baseTimer) / 1000);
                String hh = new DecimalFormat("00").format(time / 3600);
                String mm = new DecimalFormat("00").format(time % 3600 / 60);
                String ss = new DecimalFormat("00").format(time % 60);
                String timeFormat = new String(hh + ":" + mm + ":" + ss);
                Message msg = new Message();
                msg.obj = timeFormat;
                startTimehandler.sendMessage(msg);
                timer.cancel();

                setContentView(R.layout.fitting_timer_save);

            }
        });
    }
}
