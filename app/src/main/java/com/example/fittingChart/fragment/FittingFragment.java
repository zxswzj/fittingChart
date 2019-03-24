package com.example.fittingChart.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.activity.FittingActivity;
import com.example.fittingChart.module.Data;
import com.example.fittingChart.module.FittingTableData;
import com.example.fittingChart.ui.CustomViewPager.CustomPagerAdapter;
import com.example.fittingChart.ui.MyListView.MyListViewAdapter;

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
//    List<Fragment> fragments1 = new ArrayList<>();
//    List<Fragment> fragments2 = new ArrayList<>();
//    MyFragmentPagerAdapter adapter1;
//    MyFragmentPagerAdapter adapter2;
//    MyListFragment fragBreast;
//    MyListFragment fragShoulder;
//    MyListFragment fragArm;
//    MyListFragment fragBack;
//    MyListFragment fragLeg;
//    MyListFragment fragBelly;

    View view1,view2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "FittingFragment.onCreate");
        String[] strBreast = {"俯卧撑","跪姿俯卧撑","扶墙俯卧撑","哑铃夹胸","+"};
        String[] strShoulder = {"哑铃前平举","哑铃侧平举","哑铃飞鸟","+"};
        String[] strArm = {"哑铃肱二头肌弯举","哑铃肱三头肌弯举","+"};
        String[] strBack = {"哑铃飞鸟","+"};
        String[] strBelly = {"卷腹","仰卧起坐(伤尾椎骨哦)","+"};
        String[] strLeg = {"自重深蹲","负重深蹲","靠墙深蹲","+"};
        String[] strBrain = {"最强大脑之数字迷宫","+"};
        String[] strOthers = {"体重","跑步","+"};
//        view1 = getLayoutInflater().inflate(R.layout.fitting_list,null);
//        view2 = getLayoutInflater().inflate(R.layout.fitting_list,null);




//        Bundle bundleBreast = new Bundle();
//        bundleBreast.putStringArray("list",strBreast);
//        fragBreast = new MyListFragment();
//        fragBreast.setArguments(bundleBreast);
//        fragments1.add(fragBreast);
//
//        Bundle bundleShoulder = new Bundle();
//        bundleShoulder.putStringArray("list",strShoulder);
//        fragShoulder = new MyListFragment();
//        fragShoulder.setArguments(bundleShoulder);
//        fragments1.add(fragShoulder);
//
//        Bundle bundleArm = new Bundle();
//        bundleArm.putStringArray("list",strArm);
//        fragArm = new MyListFragment();
//        fragArm.setArguments(bundleArm);
//        fragments1.add(fragArm);
//
//        Bundle bundleBack = new Bundle();
//        bundleBack.putStringArray("list",strBack);
//        fragBack = new MyListFragment();
//        fragBack.setArguments(bundleBack);
//        fragments1.add(fragBack);
//
//        Bundle bundleBelly = new Bundle();
//        bundleBelly.putStringArray("list",strBelly);
//        fragBelly = new MyListFragment();
//        fragBelly.setArguments(bundleBelly);
//        fragments1.add(fragBelly);
//
//        Bundle bundleLeg = new Bundle();
//        bundleLeg.putStringArray("list",strLeg);
//        fragLeg = new MyListFragment();
//        fragLeg.setArguments(bundleLeg);
//        fragments1.add(fragLeg);

//        adapter1 = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
//        adapter1.addTitlesAndFragments(mTitles1, fragments1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "FittingFragment.onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fitting, container, false);

        view1 = getLayoutInflater().inflate(R.layout.fitting_list,null);
        view2 = getLayoutInflater().inflate(R.layout.fitting_list,null);
        //TabLayout list
        List<View> viewList1 = new ArrayList<>();
        List<View> viewList2 = new ArrayList<>();
        viewList1.add(view1);
        viewList1.add(view2);
//        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
//        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList1.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList2.add(getLayoutInflater().inflate(R.layout.fitting_list,null));
        viewList2.add(getLayoutInflater().inflate(R.layout.fitting_list,null));

        tabLayout1 = view.findViewById(R.id.tl_tab1);
        viewPager1 = view.findViewById(R.id.vp_content1);
        viewPager1.setAdapter(new CustomPagerAdapter(getContext(),viewList1, tabname1));
        tabLayout1.setupWithViewPager(viewPager1); //关联TabLayout和ViewPager
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
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

//        Bundle bundleBrain = new Bundle();
//        bundleBrain.putStringArray("list",strBrain);
//        MyListFragment fragBrain = new MyListFragment();
//        fragBrain.setArguments(bundleBrain);
//        fragments2.add(fragBrain);
//        Bundle bundleOthers = new Bundle();
//        bundleOthers.putStringArray("list",strOthers);
//        MyListFragment fragOthers = new MyListFragment();
//        fragOthers.setArguments(bundleOthers);
//        fragments2.add(fragOthers);

        //viewPager1.setAdapter(adapter1); // 给ViewPager设置适配器
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
        //adapter2 = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        //adapter2.addTitlesAndFragments(mTitles2, fragments2);

        Data data = (Data)getActivity().getApplication();
        List<FittingTableData> breastList = data.breastList;
        //ListItem
        MyListViewAdapter myListViewBreastAdapter = new MyListViewAdapter(getContext(),breastList);
        ((ListView)viewList1.get(0).findViewById(R.id.fitting_lv)).setAdapter(myListViewBreastAdapter);
        ((ListView)viewList1.get(0).findViewById(R.id.fitting_lv)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        List<FittingTableData> shoulderList = data.shoulderList;
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

        List<FittingTableData> armList = data.armList;
        MyListViewAdapter myListViewArmAdapter = new MyListViewAdapter(getContext(),data.armList);
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

        MyListViewAdapter myListViewBackAdapter = new MyListViewAdapter(getContext(),data.backList);
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

        MyListViewAdapter myListViewBellyAdapter = new MyListViewAdapter(getContext(),data.bellyList);
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

        MyListViewAdapter myListViewLegAdapter = new MyListViewAdapter(getContext(),data.legList);
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

        MyListViewAdapter myListViewBrainAdapter = new MyListViewAdapter(getContext(),data.brainList);
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

        MyListViewAdapter myListViewOtherAdapter = new MyListViewAdapter(getContext(),data.otherList);
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
        Log.i(TAG, "FittingFragment.onDestroyView");

    }


    public FittingFragment() {
        // Required empty public constructor
        Log.i(TAG, "FittingFragment.FragmentActivity");
    }

}