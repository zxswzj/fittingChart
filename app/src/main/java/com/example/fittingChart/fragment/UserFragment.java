package com.example.fittingChart.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.module.Data;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.database.DBHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    View view;
    private EditText et_user;
    private EditText et_slogan;
    private ImageView iv_user;
    private Button bt_update;
    private OnFragmentInteractionListener listener;
    LineChart pushupchart;
    public UserFragment() {
        // Required empty public constructor
    }


    //在Fragment被绘制后，调用此方法，可以初始化控件资源。
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Fragment", "UserFragment.onViewCreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Fragment", "UserFragment.onCreateView");
        view =  inflater.inflate(R.layout.fragment_user, container, false);
        et_user = (EditText)view.findViewById(R.id.fragUser_userName);
        et_slogan = (EditText)view.findViewById(R.id.fragUser_slogan);
        bt_update = (Button)view.findViewById(R.id.fragUser_btn_update);
        pushupchart = (LineChart) view.findViewById(R.id.chart1);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Fragment", "UserFragment.onDestroyView");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment", "UserFragment.onPause");
    }

    //当onCreate()，onCreateView()，onViewCreated()方法执行完后调用，也就是Activity被渲染绘制出来后。
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Fragment", "UserFragment.onActivityCreated");
        DBHelper db = new DBHelper(getContext());
        ArrayList<FittingData> pushuplist = db.getAllFitting("pushup");

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < pushuplist.size(); i++) {
            Date d = new Date(pushuplist.get(i).getLocalTime());
            entries.add(new Entry(i, pushuplist.get(i).getNumber()));//new Random().nextInt(300)
        }
        if(pushuplist.size() != 0){
            LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
            LineData lineData = new LineData(dataSet);
            pushupchart.setData(lineData);
        }


        Bundle bundle = getArguments();
        et_user.setText(bundle.getString("username"));
        et_slogan.setText(bundle.getString("slogan"));
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("username",et_user.getText().toString());
                bundle.putString("slogan",et_slogan.getText().toString());
                listener.OnClicked(et_user.getText().toString(), et_slogan.getText().toString());
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Fragment", "UserFragment.onDetach");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Fragment", "UserFragment.onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "UserFragment.onCreate");

    }

    public interface OnFragmentInteractionListener {
        void OnClicked(String name, String slogan);
    }


    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     * @param date 日期
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 功能描述：返回月
     *
     * @param date
     *            Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日期
     *
     * @param date
     *            Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     *            Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date
     *            日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();

    }
}
