package com.example.fittingChart.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.activity.FittingActivity;
import com.example.fittingChart.database.MyDatabaseAdapter;
import com.example.fittingChart.module.Data;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.module.FittingTableData;
import com.example.fittingChart.ui.CustomViewPager.CustomPagerAdapter;
import com.example.fittingChart.ui.MyListView.MyListViewAdapter;
import com.example.fittingChart.ui.SwipeList.MySwipeListAdapter;
import com.example.fittingChart.ui.SwipeList.SwipeMenu;
import com.example.fittingChart.ui.SwipeList.SwipeMenuCreator;
import com.example.fittingChart.ui.SwipeList.SwipeMenuItem;
import com.example.fittingChart.ui.SwipeList.SwipeMenuListView;
import com.example.fittingChart.util.PinyinUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FittingFragment extends Fragment {
    String TAG ="TabLayout";

//    View view;
    private ArrayList<String> tab1Name = new ArrayList<>();
    private ArrayList<String> tab2Name = new ArrayList<>();
    TabLayout tabLayout1,tabLayout2;
//    ViewPager viewPager1,viewPager2;
//    View view1,view2;
    MyDatabaseAdapter dbAdapter;
    //MyListFragment myListFragment = new MyListFragment();
    ArrayList<FittingTableData> ftdList1 = new ArrayList<>();
    ArrayList<FittingTableData> ftdList2 = new ArrayList<>();

    //ArrayList<String> mAppList = new ArrayList<>();
    private MySwipeListAdapter mListAdapter1;
    private MySwipeListAdapter mListAdapter2;
    private SwipeMenuListView mListView1;
    private SwipeMenuListView mListView2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "FittingFragment.onCreate");
        tab1Name.clear();
        tab1Name.add("胸部");
        tab1Name.add("腹部");
        tab1Name.add("肩部");
        tab1Name.add("背部");
        tab1Name.add("手臂");
        tab1Name.add("腿部");
        tab2Name.clear();
        tab2Name.add("脑部");
        tab2Name.add("？？？");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "FittingFragment.onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fitting, container, false);
        dbAdapter = new MyDatabaseAdapter(getContext());
        dbAdapter.open();

        tabLayout1 = view.findViewById(R.id.tl_tab1);
        tabLayout2 = view.findViewById(R.id.tl_tab2);
        mListView1 = view.findViewById(R.id.listView1);
        mListView2 = view.findViewById(R.id.listView2);


        //viewPager1 = view.findViewById(R.id.vp_content1);
        //viewPager1.setAdapter(new CustomPagerAdapter(getContext(),viewList1, tabname1));
        for(int i=0;i<tab1Name.size();i++){
            tabLayout1.addTab(tabLayout1.newTab());
            tabLayout1.getTabAt(i).setText(tab1Name.get(i));
        }

        for(int i=0;i<tab2Name.size();i++){
            tabLayout2.addTab(tabLayout2.newTab());
            tabLayout2.getTabAt(i).setText(tab2Name.get(i));
        }

        ftdList1 = dbAdapter.getAllFittingTable("fitting_breast");
        ftdList2 = dbAdapter.getAllFittingTable("fitting_brain");

        FittingTableData d = new FittingTableData("add","ADD","增加新的运动", R.drawable.icon_add);
        ftdList1.add(d);
        ftdList2.add(d);

        mListAdapter1 = new MySwipeListAdapter(getContext(),ftdList1);
        mListView1.setAdapter(mListAdapter1);

        mListAdapter2 = new MySwipeListAdapter(getContext(),ftdList2);
        mListView2.setAdapter(mListAdapter2);

        // 第1步：设置创建器，并且在其中生成我们需要的菜单项，将其添加进菜单中
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // 创建“打开”项
                SwipeMenuItem openItem = new SwipeMenuItem(getContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9,0xC9,0xCE)));
                openItem.setWidth(dp2px(90));
                //openItem.setTitle("图表");
                openItem.setIcon(R.drawable.icon_add);
                //openItem.setTitleSize(18);
                //openItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(openItem);

                // 创建“删除”项
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xC9,0xC9, 0xCE)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.ic_delete_forever_black_24dp);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(deleteItem);
            }
        };
        // 为ListView设置创建器
        mListView1.setMenuCreator(creator);
        mListView2.setMenuCreator(creator);


        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + " item clicked", Toast.LENGTH_SHORT).show();
                //TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.tv_name);
                final String dbName = (String)tv_Name.getTag();
                if(dbName.equals("ADD")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setTitle("添加新的运动");
                    final EditText et = new EditText(getContext());
                    et.setInputType(InputType.TYPE_CLASS_TEXT);
                    alertDialogBuilder.setView(et);
                    alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = et.getText().toString();
                            if (TextUtils.isEmpty(name)) {
                                Toast.makeText(getContext(), "No hanzi, please input again.", Toast.LENGTH_SHORT).show();
                            } else {
                                StringBuilder builder = new StringBuilder();
                                //builder.append(PinyinUtils.toPinyinString(dbName, PinyinUtils.CASE_CAPITALIZE));
                                String dbNamePinyin = PinyinUtils.toPinyinString(name, PinyinUtils.CASE_CAPITALIZE);
                                String table;
                                switch(tabLayout1.getSelectedTabPosition()){
                                    case 0:table = "fitting_breast";break;
                                    case 1:table = "fitting_belly";break;
                                    case 2:table = "fitting_shoulder";break;
                                    case 3:table = "fitting_back";break;
                                    case 4:table = "fitting_arm";break;
                                    case 5:table = "fitting_leg";break;
                                    default:table = "";break;
                                }
                                dbAdapter.open();
                                ArrayList<FittingTableData> fittingTableData = dbAdapter.getAllFittingTable(table);
                                for (int i = 0; i < fittingTableData.size(); i++) {
                                    if (fittingTableData.get(i).getDbName().equals(dbNamePinyin)) {
                                        AlertDialog.Builder builderDuplicate = new AlertDialog.Builder(getContext());
                                        builderDuplicate.setTitle("???");
                                        builderDuplicate.setPositiveButton("名字重了知道不？退下重输！！！", null);
                                        builderDuplicate.create().show();
                                        return;
                                    }
                                }
                                FittingTableData ftd = new FittingTableData(name, dbNamePinyin, "user input activity", R.drawable.pushup);
                                FittingTableData ftdAdd = new FittingTableData("add","ADD","增加新的运动", R.drawable.icon_add);
                                dbAdapter.addFittingTableItem(table, ftd);
                                dbAdapter.createFitting(dbNamePinyin);
                                dbAdapter.close();
                                ftdList1.clear();
                                ftdList1.addAll(fittingTableData);
                                ftdList1.add(ftd);
                                ftdList1.add(ftdAdd);
                                mListAdapter1.notifyDataSetChanged();
                                return;
                            }
                        }
                    });
                    alertDialogBuilder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else{
                    Intent i = new Intent(getActivity(), FittingActivity.class);
                    i.putExtra("tableName", tv_Name.getText());
                    i.putExtra("tableDBName", dbName);
                    startActivity(i);
                }
            }
        });

        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + " item clicked", Toast.LENGTH_SHORT).show();
                //TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.tv_name);
                final String dbName = (String)tv_Name.getTag();
                if(dbName.equals("ADD")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setTitle("添加新的运动");
                    final EditText et = new EditText(getContext());
                    et.setInputType(InputType.TYPE_CLASS_TEXT);
                    alertDialogBuilder.setView(et);
                    alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = et.getText().toString();
                            if (TextUtils.isEmpty(name)) {
                                Toast.makeText(getContext(), "No hanzi, please input again.", Toast.LENGTH_SHORT).show();
                            } else {
                                StringBuilder builder = new StringBuilder();
                                String dbNamePinyin = PinyinUtils.toPinyinString(name, PinyinUtils.CASE_CAPITALIZE);
                                String table;
                                switch(tabLayout2.getSelectedTabPosition()){
                                    case 0:table = "fitting_brain";break;
                                    case 1:table = "fitting_other";break;
                                    case 2:table = "fitting_shoulder";break;
                                    default:table = "";break;
                                }
                                dbAdapter.open();
                                ArrayList<FittingTableData> fittingTableData = dbAdapter.getAllFittingTable(table);
                                for (int i = 0; i < fittingTableData.size(); i++) {
                                    if (fittingTableData.get(i).getDbName().equals(dbNamePinyin)) {
                                        AlertDialog.Builder builderDuplicate = new AlertDialog.Builder(getContext());
                                        builderDuplicate.setTitle("???");
                                        builderDuplicate.setPositiveButton("名字重了知道不？退下重输！！！", null);
                                        builderDuplicate.create().show();
                                        return;
                                    }
                                }
                                FittingTableData ftd = new FittingTableData(name, dbNamePinyin, "user input activity", R.drawable.pushup);
                                FittingTableData ftdAdd = new FittingTableData("add","ADD","增加新的运动", R.drawable.icon_add);
                                dbAdapter.addFittingTableItem(table, ftd);
                                dbAdapter.createFitting(dbNamePinyin);
                                dbAdapter.close();
                                ftdList2.clear();
                                ftdList2.addAll(fittingTableData);
                                ftdList2.add(ftd);
                                ftdList2.add(ftdAdd);
                                mListAdapter2.notifyDataSetChanged();
                                return;
                            }
                        }
                    });
                    alertDialogBuilder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else{
                    Intent i = new Intent(getActivity(), FittingActivity.class);
                    i.putExtra("tableName", tv_Name.getText());
                    i.putExtra("tableDBName", dbName);
                    startActivity(i);
                }
            }
        });

        // 第2步：为ListView设置菜单项点击监听器，来监听菜单项的点击事件
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
//                    delete(item);
                        String table;
                        switch(tabLayout1.getSelectedTabPosition()){
                            case 0:table = "fitting_breast";break;
                            case 1:table = "fitting_belly";break;
                            case 2:table = "fitting_shoulder";break;
                            case 3:table = "fitting_back";break;
                            case 4:table = "fitting_arm";break;
                            case 5:table = "fitting_leg";break;
                            default:table = "";break;
                        }
                        dbAdapter.open();
                        dbAdapter.deleteFittingTableItem(table, ftdList1.get(position).getName());
                        dbAdapter.close();
                        ftdList1.remove(position);
                        //通知监听者数据集发生改变，更新ListView界面
                        Log.i(TAG, "FittingFragment.mListView.setOnMenuItemClickListener tab=" + tabLayout1.getSelectedTabPosition() + "item=" + position);
                        mListAdapter1.notifyDataSetChanged();
                        break;
                }
                // true：其他已打开的列表项的菜单状态将保持原样，不会受到其他列表项的影响而自动收回
                // false:已打开的列表项的菜单将自动收回
                return false;
            }
        });

        mListView2.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
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
//                    delete(item);
                        String table;
                        switch(tabLayout2.getSelectedTabPosition()){
                            case 0:table = "fitting_brain";break;
                            case 1:table = "fitting_other";break;
                            default:table = "";break;
                        }
                        dbAdapter.open();
                        dbAdapter.deleteFittingTableItem(table, ftdList2.get(position).getName());
                        dbAdapter.close();
                        ftdList2.remove(position);
                        //通知监听者数据集发生改变，更新ListView界面
                        Log.i(TAG, "FittingFragment.mListView.setOnMenuItemClickListener tab=" + tabLayout2.getSelectedTabPosition() + "item=" + position);
                        mListAdapter2.notifyDataSetChanged();
                        break;
                }
                // true：其他已打开的列表项的菜单状态将保持原样，不会受到其他列表项的影响而自动收回
                // false:已打开的列表项的菜单将自动收回
                return false;
            }
        });


        // 设置侧滑监听器，监听侧滑开始和侧滑结束
        // 注意：当我们将一个已经侧滑出来的菜单重新收回去的时候并不会调用onSwipeStart方法，
        // 但是结束的时候依然会调用onSwipeEnd方法
        mListView1.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // 设置监听Menu状态改变的监听器（Menu的打开和关闭）
        mListView1.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });

        // other setting
//        listView.setCloseInterpolator(new BounceInterpolator());

// 设置列表项长点击的监听器
//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), position + " long click", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_fitting_container, myListFragment);
//        ft.commit();

        //tabLayout1.setupWithViewPager(viewPager1); //关联TabLayout和ViewPager
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabSelected" + tab.getPosition() + tab.getText());
                ArrayList<FittingTableData> ftdList1Tmp = new ArrayList<>();
                dbAdapter.open();
                switch (tab.getPosition()){
                    case 0:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_breast");break;
                    case 1:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_belly");break;
                    case 2:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_shoulder");break;
                    case 3:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_back");break;
                    case 4:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_arm");break;
                    case 5:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_leg");break;
                    default:break;
                }
                dbAdapter.close();
                FittingTableData d = new FittingTableData("add","ADD","增加新的运动", R.drawable.icon_add);
                ftdList1.clear();
                ftdList1.addAll(ftdList1Tmp);
                ftdList1.add(d);
                mListAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabUnselected");

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabReselected");

            }
        });

        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabSelected" + tab.getPosition() + tab.getText());
                ArrayList<FittingTableData> ftdList1Tmp = new ArrayList<>();
                dbAdapter.open();
                switch (tab.getPosition()){
                    case 0:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_brain");break;
                    case 1:ftdList1Tmp = dbAdapter.getAllFittingTable("fitting_other");break;
                    default:break;
                }
                dbAdapter.close();
                FittingTableData d = new FittingTableData("add","ADD","增加新的运动", R.drawable.icon_add);
                ftdList2.clear();
                ftdList2.addAll(ftdList1Tmp);
                ftdList2.add(d);
                mListAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabUnselected");

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabReselected");

            }
        });

        dbAdapter.close();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "FittingFragment.onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "FittingFragment.onAttach");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "FittingFragment.onResume");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "FittingFragment.onStart");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "FittingFragment.onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "FittingFragment.onStop");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "FittingFragment.onDetach");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbAdapter.close();
        Log.i(TAG, "FittingFragment.onDestroyView");

    }

    private int dp2px(int value) {
        // 第一个参数为我们待转的数据的单位，此处为 dp（dip）
        // 第二个参数为我们待转的数据的值的大小
        // 第三个参数为此次转换使用的显示量度（Metrics），它提供屏幕显示密度（density）和缩放信息
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }

    public FittingFragment() {
        // Required empty public constructor
        Log.i(TAG, "FittingFragment.FragmentActivity");
    }

}