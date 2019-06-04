package com.example.fittingChart.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fittingChart.R;
import com.example.fittingChart.database.DaoSession;
import com.example.fittingChart.database.FittingItem;
import com.example.fittingChart.database.GreenDaoHelper;
import com.example.fittingChart.database.ShowTable;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private DaoSession session;


    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = GreenDaoHelper.getDaoSession(getActivity());

        session.getUserDao().deleteAll();//清空所有记录
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        LineChart lineChart = view.findViewById(R.id.fragment_chart_container);
        TextView tv = view.findViewById(R.id.fragment_chart_tv);

        Bundle bundle = getArguments();
        int fragmentID = bundle.getInt("fragmentID");

        List<ShowTable> showTables = session.getShowTableDao().loadAll();
        int tablesize = showTables.size();
        if(tablesize != 0) {
            String tablename = new String();
            String tableDBName = new String();
            int tablecount = 0;
            switch ((fragmentID)){
                case R.id.fragment_record_fl1:
                    tablename = tables.get(0).getName();
                    tableDBName = tables.get(0).getDBName();
                    tablecount = tables.get(0).getCount();
                    break;
                case R.id.fragment_record_fl2:
                    if(tablesize >= 2){
                        tablename = tables.get(1).getName();
                        tableDBName = tables.get(1).getDBName();
                        tablecount = tables.get(1).getCount();
                        break;
                    }
                case R.id.fragment_record_fl3:
                    if(tablesize >= 3){
                        tablename = tables.get(2).getName();
                        tableDBName = tables.get(2).getDBName();
                        tablecount = tables.get(2).getCount();
                        break;
                    }
                case R.id.fragment_record_fl4:
                    if(tablesize >= 4){
                        tablename = tables.get(3).getName();
                        tableDBName = tables.get(3).getDBName();
                        tablecount = tables.get(3).getCount();
                        break;
                    }
                case R.id.fragment_record_fl5:
                    if(tablesize >= 5){
                        tablename = tables.get(4).getName();
                        tableDBName = tables.get(4).getDBName();
                        tablecount = tables.get(4).getCount();
                        break;
                    }
                case R.id.fragment_record_fl6:
                    if(tablesize >= 6){
                        tablename = tables.get(5).getName();
                        tableDBName = tables.get(5).getDBName();
                        tablecount = tables.get(5).getCount();
                        break;
                    }
                case R.id.fragment_record_fl7:
                    if(tablesize >= 7){
                        tablename = tables.get(6).getName();
                        tableDBName = tables.get(6).getDBName();
                        tablecount = tables.get(6).getCount();
                        break;
                    }
                case R.id.fragment_record_fl8:
                    if(tablesize >= 8){
                        tablename = tables.get(7).getName();
                        tableDBName = tables.get(7).getDBName();
                        tablecount = tables.get(7).getCount();
                        break;
                    }
                default:break;
            }

            if(!tablename.isEmpty()){
                tv.setText(tablename + "： 您已经运动" + tablecount + "次啦啊哈哈哈");
                ArrayList<FittingItem> fitting = dbAdapter.getAllFitting(tableDBName);
                List<Entry> entries = new ArrayList<>();
                for (int i = 0; i < fitting.size(); i++) {
                    Date d = new Date(fitting.get(i).getLocalTime());
                    entries.add(new Entry(i, fitting.get(i).getNumber()));//new Random().nextInt(300)
                }
                if (fitting.size() != 0) {
                    LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
                    LineData lineData = new LineData(dataSet);
                    lineChart.setData(lineData);
                }
            }
        }

        return view;
    }

}
