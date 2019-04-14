package com.example.fittingChart.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.fittingChart.R;
import com.example.fittingChart.database.MyDatabaseAdapter;
import com.example.fittingChart.module.Data;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.module.FittingSwipeItemData;
import com.example.fittingChart.module.FittingTableData;
import com.example.fittingChart.ui.SwipeList.MyFittingDataSwipeListAdapter;
import com.example.fittingChart.ui.SwipeList.MySwipeListAdapter;
import com.example.fittingChart.ui.SwipeList.SwipeMenu;
import com.example.fittingChart.ui.SwipeList.SwipeMenuCreator;
import com.example.fittingChart.ui.SwipeList.SwipeMenuItem;
import com.example.fittingChart.ui.SwipeList.SwipeMenuListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
    Button bt_f1, bt_f2;
    Button bt_f3, bt_f4, bt_f5;
    private TimePicker timePicker;
    private SwipeMenuListView mListView1;
    ArrayList<FittingSwipeItemData> swipeItemDataList1 = new ArrayList<>();
    private MyFittingDataSwipeListAdapter mListAdapter1;


    long lHour,lMinute,lSecond;
    long restSecond = 0;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Fragment", "FittingStartFragment.onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fitting_start, container, false);
        timer = new Timer();
        bt_f1 = view.findViewById(R.id.fitting_btn_f1);
        bt_f1.setFocusable(true);
        bt_f1.setFocusableInTouchMode(true);
        bt_f2 = view.findViewById(R.id.fitting_btn_f2);
        bt_f3 = view.findViewById(R.id.fitting_btn_f3);
        bt_f4 = view.findViewById(R.id.fitting_btn_f4);
        bt_f5 = view.findViewById(R.id.fitting_btn_f5);

        //timePicker = view.findViewById(R.id.fitting_timePicker);
        et_num = view.findViewById(R.id.fitting_pt_num);
        et_duration = view.findViewById(R.id.fitting_pt_duration);
        mListView1 = view.findViewById(R.id.listView1);


        bt_f1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.i("Fragment", "FittingStartFragment.bt_start.onClick");
                 if (bt_f1.getText().equals("开始运动")) {

                     baseTimer = (int) SystemClock.elapsedRealtime();
                     timer.scheduleAtFixedRate(new TimerTask() {
                         @Override
                         public void run() {
                             int time = (int) ((SystemClock.elapsedRealtime() - baseTimer) / 1000);
                             String hh = new DecimalFormat("00").format(time / 3600);
                             String mm = new DecimalFormat("00").format(time % 3600 / 60);
                             String ss = new DecimalFormat("00").format(time % 60);
                             String timeFormat = new String(hh + ":" + mm + ":" + ss);
                             Message msg = new Message();
                             msg.obj = timeFormat;
                             startTimehandler.sendMessage(msg);
                         }
                     }, 0, 1000L);
                     bt_f1.setText("停止");
                 } else if (bt_f1.getText().equals("停止")) {
                     bt_f1.setText("保存记录");
                 }else if (bt_f1.getText().equals("保存记录")) {
                     FittingSwipeItemData fd = new FittingSwipeItemData();
                     fd.setName(tableName);
                     fd.setResourceID(R.drawable.dumbbell2);
                     fd.setNumber(Integer.parseInt(et_num.getEditableText().toString().trim()));
                     fd.setDurationTime(SystemClock.elapsedRealtime() - baseTimer);
                     fd.setRestTime(restSecond);
                     fd.setLocalTime(new Date().getTime());
                     fd.setDes("aaa");
                     swipeItemDataList1.add(fd);
                     mListAdapter1.notifyDataSetChanged();
                     bt_f1.setText("再来一组");
                 }
                 else if (bt_f1.getText().equals("再来一组")) {

                     bt_f1.setText("停止");
                 }
             }
         });
        bt_f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Fragment", "FittingStartFragment.bt_stop.onClick");
                if(bt_f2.getText().equals("点错了，打扰了")) {

                    lSecond = (int) ((SystemClock.elapsedRealtime() - baseTimer) / 1000);
                    timer.cancel();

                    String hh = new DecimalFormat("00").format(lSecond / 3600);
                    String mm = new DecimalFormat("00").format(lSecond % 3600 / 60);
                    String ss = new DecimalFormat("00").format(lSecond % 60);
                    String timeFormat = new String(hh + ":" + mm + ":" + ss);

                    et_duration.setText(timeFormat);
                }
            }
        });

        bt_f3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 restSecond += 10000;
             }
         });

        bt_f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restSecond += 30000;
            }
        });

        bt_f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restSecond += 60000;
            }
        });

//        timePicker.setIs24HourView(true);
//        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker view, int hourOfDay,
//                                      int minute) {
//                Toast.makeText(getActivity(),
//                        hourOfDay + "小时" + minute + "分钟",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Data data = (Data)getActivity().getApplication();
//                MyDatabaseAdapter dbAdapter = new MyDatabaseAdapter(getContext());
//                FittingData fd = new FittingData();
//                fd.setNumber(Integer.parseInt(et_num.getEditableText().toString().trim()));
//                fd.setDurationTime(lSecond);
//                fd.setLocalTime(new Date().getTime());
//                fd.setDes("aaa");
//
//                dbAdapter.open();
//                dbAdapter.addFittingItem(tableDBName, fd);
//                dbAdapter.addShowTableItem(tableName, tableDBName);
//                dbAdapter.close();
//            }
//        });
//
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        mListAdapter1 = new MyFittingDataSwipeListAdapter(getContext(),swipeItemDataList1);
        mListView1.setAdapter(mListAdapter1);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // 创建“打开”项
                SwipeMenuItem openItem = new SwipeMenuItem(getContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9,0xC9,0xCE)));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("图表");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(openItem);

                // 创建“删除”项
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.icon_add);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(deleteItem);
            }
        };
        // 为ListView设置创建器
        mListView1.setMenuCreator(creator);

        mListView1.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //position:列表项的下标。如：0，1，2，3，4，...
                //index:菜单项的下标。如：0，1，2，3，4，...
                //ApplicationInfo item = mAppList.get(position);
                //String str = breastList.get(position);
                switch (index) {
                    case 0:
                        // open
                        //open(item);
                        break;
                    case 1:
                        // delete
                        mListAdapter1.notifyDataSetChanged();
                        break;
                }
                // true：其他已打开的列表项的菜单状态将保持原样，不会受到其他列表项的影响而自动收回
                // false:已打开的列表项的菜单将自动收回
                return false;
            }
        });

        return view;
    }

    private int dp2px(int value) {
        // 第一个参数为我们待转的数据的单位，此处为 dp（dip）
        // 第二个参数为我们待转的数据的值的大小
        // 第三个参数为此次转换使用的显示量度（Metrics），它提供屏幕显示密度（density）和缩放信息
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }

}
