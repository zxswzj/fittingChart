package com.example.fittingChart.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fittingChart.R;
import com.github.mikephil.charting.charts.LineChart;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {


    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        LineChart lineChart = view.findViewById(R.id.fragment_chart);

        return view;
    }

}
