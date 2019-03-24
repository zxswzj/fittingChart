package com.example.fittingChart.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fittingChart.R;
import com.example.fittingChart.database.DBHelper;
import com.example.fittingChart.module.FittingData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {
    String TAG = "LineChart";

    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    //fragment的onCreateView方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "RecordFragment.onCreateView");
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        LineChart chart = view.findViewById(R.id.chart111);
        LinearLayout rl = view.findViewById(R.id.empty_linear_layout_ll);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ChartFragment chartFragment1 = new ChartFragment();
        ChartFragment chartFragment2 = new ChartFragment();
        ChartFragment chartFragment3 = new ChartFragment();

        transaction.replace(R.id.fragment_record_fl1, chartFragment1);
        transaction.replace(R.id.fragment_record_fl2, chartFragment2);
        transaction.replace(R.id.fragment_record_fl3, chartFragment3);

        FrameLayout fl4 = view.findViewById(R.id.fragment_record_fl4);
        FrameLayout fl5 = view.findViewById(R.id.fragment_record_fl5);
        FrameLayout fl6 = view.findViewById(R.id.fragment_record_fl6);
        FrameLayout fl7 = view.findViewById(R.id.fragment_record_fl7);
        FrameLayout fl8 = view.findViewById(R.id.fragment_record_fl8);
        fl4.setVisibility(View.GONE);
        fl5.setVisibility(View.GONE);
        fl6.setVisibility(View.GONE);
        fl7.setVisibility(View.GONE);
        fl8.setVisibility(View.GONE);


        transaction.addToBackStack(null);
        transaction.commit();
//        rl.addView(chart);
//
//        List<Entry> lineEntry = new ArrayList<>();
//
//        for(int i=0;i<4;i++)
//        {
//            lineEntry.add(new Entry(i, new Random().nextInt(10)));
//        }
//
//        LineDataSet lineDataSet = new LineDataSet(lineEntry, "linechart");
//        lineDataSet.setColor(R.color.Chocolate);
//        lineDataSet.setValueTextColor(R.color.AliceBlue);
//
//        LineData lineData = new LineData(lineDataSet);
//        chart.setData(lineData);

        return view;
    }
}
