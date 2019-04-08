package com.example.fittingChart.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.fittingChart.util.PinyinUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FittingFragment extends Fragment {
    String TAG ="TabLayout";

//    View view;
    private String [] tabname1 = {"胸", "肩", "手臂","背","腹","腿"};
    private String [] tabname2 = {"大脑","???"};
    TabLayout tabLayout1,tabLayout2;
    ViewPager viewPager1,viewPager2;
    View view1,view2;
    MyDatabaseAdapter dbAdapter;
    MyListFragment myListFragment = new MyListFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "FittingFragment.onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "FittingFragment.onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fitting, container, false);
        dbAdapter = new MyDatabaseAdapter(getContext());
        dbAdapter.open();

        //view1 = getLayoutInflater().inflate(R.layout.fitting_list,null);
        //view2 = getLayoutInflater().inflate(R.layout.fitting_list,null);
        //TabLayout list
        List<View> viewList1 = new ArrayList<>();
        List<View> viewList2 = new ArrayList<>();
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList2.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList2.add(getLayoutInflater().inflate(R.layout.fitting_list,null));

        tabLayout1 = view.findViewById(R.id.tl_tab1);
        //viewPager1 = view.findViewById(R.id.vp_content1);
        //viewPager1.setAdapter(new CustomPagerAdapter(getContext(),viewList1, tabname1));
        tabLayout1.addTab(tabLayout1.newTab());
        tabLayout1.getTabAt(0).setText("胸部");
        tabLayout1.addTab(tabLayout1.newTab());
        tabLayout1.getTabAt(1).setText("腹部");
        tabLayout1.addTab(tabLayout1.newTab());
        tabLayout1.getTabAt(2).setText("肩部");
        tabLayout1.addTab(tabLayout1.newTab());
        tabLayout1.getTabAt(3).setText("背部");
        tabLayout1.addTab(tabLayout1.newTab());
        tabLayout1.getTabAt(4).setText("手臂");
        tabLayout1.addTab(tabLayout1.newTab());
        tabLayout1.getTabAt(5).setText("腿部");

        //tabLayout1.setupWithViewPager(viewPager1); //关联TabLayout和ViewPager
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabSelected" + tab.getPosition() + tab.getText());
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_fitting_container, myListFragment);
                ft.commit();
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

        tabLayout2 = view.findViewById(R.id.tl_tab2);
        viewPager2 = view.findViewById(R.id.vp_content2);
        viewPager2.setAdapter(new CustomPagerAdapter(getContext(),viewList2, tabname2));
        tabLayout2.setupWithViewPager(viewPager2); //关联TabLayout和ViewPager
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "FittingFragment.onTabSelected");
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

        //Data data = (Data)getActivity().getApplication();
        //List<FittingTableData> breastList = data.breastList;
        //ListItem
        ArrayList<FittingTableData> breastList = dbAdapter.getAllFittingTable("fitting_breast");
        ArrayList<FittingTableData> armList = dbAdapter.getAllFittingTable("fitting_arm");
        ArrayList<FittingTableData> shoulderList = dbAdapter.getAllFittingTable("fitting_shoulder");
        ArrayList<FittingTableData> backList = dbAdapter.getAllFittingTable("fitting_back");
        ArrayList<FittingTableData> bellyList = dbAdapter.getAllFittingTable("fitting_belly");
        ArrayList<FittingTableData> legList = dbAdapter.getAllFittingTable("fitting_leg");
        ArrayList<FittingTableData> brainList = dbAdapter.getAllFittingTable("fitting_brain");
        ArrayList<FittingTableData> otherList = dbAdapter.getAllFittingTable("fitting_other");
        FittingTableData d = new FittingTableData("add","ADD","增加新的运动", R.drawable.icon_add);
        armList.add(d);
        breastList.add(d);
        shoulderList.add(d);
        backList.add(d);
        bellyList.add(d);
        legList.add(d);
        brainList.add(d);
        otherList.add(d);

        MyListViewAdapter myListViewBreastAdapter = new MyListViewAdapter(getContext(),breastList);
        ((ListView)viewList1.get(0).findViewById(R.id.fitting_lv)).setAdapter(myListViewBreastAdapter);
        ((ListView)viewList1.get(0).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                String dbName = tv_dbName.getText().toString();
                if(dbName.equals("ADD")) {
                    //dbAdapter.open();
                    AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setTitle("添加新的运动");
                    final EditText et = new EditText(getContext());
                    alertDialogBuilder.setView(et);
                    alertDialogBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = et.getText().toString();
                            if (TextUtils.isEmpty(name)) {
                                Toast.makeText(getContext(), "No hanzi, please input again.", Toast.LENGTH_SHORT).show();
                            } else {
                                StringBuilder builder = new StringBuilder();
                                //builder.append(PinyinUtils.toPinyinString(dbName, PinyinUtils.CASE_CAPITALIZE));
                                String dbNamePinyin = PinyinUtils.toPinyinString(name, PinyinUtils.CASE_CAPITALIZE);
                                //dbAdapter = new MyDatabaseAdapter(getContext());
                                //dbAdapter.open();
                                ArrayList<FittingTableData> fittingTableData = dbAdapter.getAllFittingTable("fitting_breast");
                                for(int i=0;i<fittingTableData.size();i++){
                                    if(fittingTableData.get(i).getDbName().equals(dbNamePinyin)){
                                        AlertDialog.Builder builderDuplicate=new AlertDialog.Builder(getContext());
                                        builderDuplicate.setTitle("???");
                                        builderDuplicate.setPositiveButton("名字重了知道不？退下重输！！！",null);
                                        builderDuplicate.create().show();
                                        //dbAdapter.close();

                                        return;
                                    }
                                }
                                FittingTableData ftd = new FittingTableData(name,dbNamePinyin,"user input activity", R.drawable.pushup);
                                dbAdapter.addFittingTableItem("fitting_breast",ftd);
                                //dbAdapter.close();
                                return;
                            }
                        }
                    });
                    alertDialogBuilder.setNegativeButton("取消",null);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else {
                    Intent i = new Intent(getActivity(), FittingActivity.class);
                    i.putExtra("tableName", tv_Name.getText());
                    i.putExtra("tableDBName", tv_dbName.getText());
                    startActivity(i);
                }
            }
        });

        MyListViewAdapter myListViewShoulderAdapter = new MyListViewAdapter(getContext(),shoulderList);
        ((ListView)viewList1.get(1).findViewById(R.id.fitting_lv)).setAdapter(myListViewShoulderAdapter);
        ((ListView)viewList1.get(1).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                Intent i = new Intent(getActivity() , FittingActivity.class);
                i.putExtra("tableName", tv_Name.getText());
                i.putExtra("tableDBName", tv_dbName.getText());
                startActivity(i);
            }
        });

        MyListViewAdapter myListViewArmAdapter = new MyListViewAdapter(getContext(),armList);
        ((ListView)viewList1.get(2).findViewById(R.id.fitting_lv)).setAdapter(myListViewArmAdapter);
        ((ListView)viewList1.get(2).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                Intent i = new Intent(getActivity() , FittingActivity.class);
                i.putExtra("tableName", tv_Name.getText());
                i.putExtra("tableDBName", tv_dbName.getText());
                startActivity(i);
            }
        });

        MyListViewAdapter myListViewBackAdapter = new MyListViewAdapter(getContext(),backList);
        ((ListView)viewList1.get(3).findViewById(R.id.fitting_lv)).setAdapter(myListViewBackAdapter);
        ((ListView)viewList1.get(3).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                Intent i = new Intent(getActivity() , FittingActivity.class);
                i.putExtra("tableName", tv_Name.getText());
                i.putExtra("tableDBName", tv_dbName.getText());
                startActivity(i);
            }
        });

        MyListViewAdapter myListViewBellyAdapter = new MyListViewAdapter(getContext(),bellyList);
        ((ListView)viewList1.get(4).findViewById(R.id.fitting_lv)).setAdapter(myListViewBellyAdapter);
        ((ListView)viewList1.get(4).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                Intent i = new Intent(getActivity() , FittingActivity.class);
                i.putExtra("tableName", tv_Name.getText());
                i.putExtra("tableDBName", tv_dbName.getText());
                startActivity(i);
            }
        });

        MyListViewAdapter myListViewLegAdapter = new MyListViewAdapter(getContext(),legList);
        ((ListView)viewList1.get(5).findViewById(R.id.fitting_lv)).setAdapter(myListViewLegAdapter);
        ((ListView)viewList1.get(5).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                Intent i = new Intent(getActivity() , FittingActivity.class);
                i.putExtra("tableName", tv_Name.getText());
                i.putExtra("tableDBName", tv_dbName.getText());
                startActivity(i);
            }
        });

        MyListViewAdapter myListViewBrainAdapter = new MyListViewAdapter(getContext(),brainList);
        ((ListView)viewList2.get(0).findViewById(R.id.fitting_lv)).setAdapter(myListViewBrainAdapter);
        ((ListView)viewList2.get(0).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                Intent i = new Intent(getActivity() , FittingActivity.class);
                i.putExtra("tableName", tv_Name.getText());
                i.putExtra("tableDBName", tv_dbName.getText());
                startActivity(i);
            }
        });

        MyListViewAdapter myListViewOtherAdapter = new MyListViewAdapter(getContext(),otherList);
        ((ListView)viewList2.get(1).findViewById(R.id.fitting_lv)).setAdapter(myListViewOtherAdapter);
        ((ListView)viewList2.get(1).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
                TextView tv_Name = view.findViewById(R.id.fitting_list_item_tv_name);
                Intent i = new Intent(getActivity() , FittingActivity.class);
                i.putExtra("tableName", tv_Name.getText());
                i.putExtra("tableDBName", tv_dbName.getText());
                startActivity(i);
            }
        });
        Log.i(TAG, "FittingFragment.onCreateView closed");
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


    public FittingFragment() {
        // Required empty public constructor
        Log.i(TAG, "FittingFragment.FragmentActivity");
    }

}