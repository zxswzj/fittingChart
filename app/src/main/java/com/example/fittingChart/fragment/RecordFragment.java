package com.example.fittingChart.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.fittingChart.R;
import com.github.mikephil.charting.charts.LineChart;


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

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ChartFragment chartFragment1 = new ChartFragment();
        ChartFragment chartFragment2 = new ChartFragment();
        ChartFragment chartFragment3 = new ChartFragment();
        ChartFragment chartFragment4 = new ChartFragment();
        ChartFragment chartFragment5 = new ChartFragment();
        ChartFragment chartFragment6 = new ChartFragment();
        ChartFragment chartFragment7 = new ChartFragment();
        ChartFragment chartFragment8 = new ChartFragment();


        transaction.replace(R.id.fragment_record_fl1, chartFragment1);
        transaction.replace(R.id.fragment_record_fl2, chartFragment2);
        transaction.replace(R.id.fragment_record_fl3, chartFragment3);
        transaction.replace(R.id.fragment_record_fl4, chartFragment4);
        transaction.replace(R.id.fragment_record_fl5, chartFragment5);
        transaction.replace(R.id.fragment_record_fl6, chartFragment6);
        transaction.replace(R.id.fragment_record_fl7, chartFragment7);
        transaction.replace(R.id.fragment_record_fl8, chartFragment8);


//        FrameLayout fl4 = view.findViewById(R.id.fragment_record_fl4);
//        FrameLayout fl5 = view.findViewById(R.id.fragment_record_fl5);
//        FrameLayout fl6 = view.findViewById(R.id.fragment_record_fl6);
//        FrameLayout fl7 = view.findViewById(R.id.fragment_record_fl7);
//        FrameLayout fl8 = view.findViewById(R.id.fragment_record_fl8);
//        fl4.setVisibility(View.GONE);
//        fl5.setVisibility(View.GONE);
//        fl6.setVisibility(View.GONE);
//        fl7.setVisibility(View.GONE);
//        fl8.setVisibility(View.GONE);

        Bundle bundle1 = new Bundle();
        bundle1.putInt("fragmentID", R.id.fragment_record_fl1);
        chartFragment1.setArguments(bundle1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("fragmentID", R.id.fragment_record_fl2);
        chartFragment2.setArguments(bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("fragmentID", R.id.fragment_record_fl3);
        chartFragment3.setArguments(bundle3);
        Bundle bundle4 = new Bundle();
        bundle4.putInt("fragmentID", R.id.fragment_record_fl4);
        chartFragment4.setArguments(bundle4);
        Bundle bundle5 = new Bundle();
        bundle5.putInt("fragmentID", R.id.fragment_record_fl5);
        chartFragment5.setArguments(bundle5);
        Bundle bundle6 = new Bundle();
        bundle6.putInt("fragmentID", R.id.fragment_record_fl6);
        chartFragment6.setArguments(bundle6);
        Bundle bundle7 = new Bundle();
        bundle7.putInt("fragmentID", R.id.fragment_record_fl7);
        chartFragment7.setArguments(bundle7);
        Bundle bundle8 = new Bundle();
        bundle8.putInt("fragmentID", R.id.fragment_record_fl8);
        chartFragment8.setArguments(bundle8);


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
