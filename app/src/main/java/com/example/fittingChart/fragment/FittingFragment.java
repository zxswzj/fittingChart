package com.example.fittingChart.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fittingChart.R;
import com.example.fittingChart.ui.TabLayout.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FittingFragment extends Fragment {
    String TAG ="TabLayout";

    View view;
    private String [] mTitles1 = {"胸", "肩", "手臂","背","腹","腿"};
    private String [] mTitles2 = {"大脑","???"};
    TabLayout tabLayout1,tabLayout2;
    ViewPager viewPager1;
    List<Fragment> fragments1 = new ArrayList<>();
    List<Fragment> fragments2 = new ArrayList<>();
    MyFragmentPagerAdapter adapter1;
    MyFragmentPagerAdapter adapter2;
    MyListFragment fragBreast;
    MyListFragment fragShoulder;
    MyListFragment fragArm;
    MyListFragment fragBack;
    MyListFragment fragLeg;
    MyListFragment fragBelly;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "FittingFragment.onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fitting, container, false);
        tabLayout1 = view.findViewById(R.id.tl_tab1);
        viewPager1 = view.findViewById(R.id.vp_content1);

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

        viewPager1.setAdapter(adapter1); // 给ViewPager设置适配器
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


//        tabLayout2 = view.findViewById(R.id.tl_tab2);
//        viewPager2 = view.findViewById(R.id.vp_content2);
//        adapter2 = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
//        adapter2.addTitlesAndFragments(mTitles2, fragments2);
//        viewPager2.setAdapter(adapter2); // 给ViewPager设置适配器
//        tabLayout2.setupWithViewPager(viewPager2); //关联TabLayout和ViewPager
//        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Log.i(TAG, "FittingFragment.onTabSelected");
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                Log.i(TAG, "FittingFragment.onTabUnselected");
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                Log.i(TAG, "FittingFragment.onTabReselected");
//
//            }
//        });
        return view;
    }

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

        Bundle bundleBreast = new Bundle();
        bundleBreast.putStringArray("list",strBreast);
        fragBreast = new MyListFragment();
        fragBreast.setArguments(bundleBreast);
        fragments1.add(fragBreast);

        Bundle bundleShoulder = new Bundle();
        bundleShoulder.putStringArray("list",strShoulder);
        fragShoulder = new MyListFragment();
        fragShoulder.setArguments(bundleShoulder);
        fragments1.add(fragShoulder);

        Bundle bundleArm = new Bundle();
        bundleArm.putStringArray("list",strArm);
        fragArm = new MyListFragment();
        fragArm.setArguments(bundleArm);
        fragments1.add(fragArm);

        Bundle bundleBack = new Bundle();
        bundleBack.putStringArray("list",strBack);
        fragBack = new MyListFragment();
        fragBack.setArguments(bundleBack);
        fragments1.add(fragBack);

        Bundle bundleBelly = new Bundle();
        bundleBelly.putStringArray("list",strBelly);
        fragBelly = new MyListFragment();
        fragBelly.setArguments(bundleBelly);
        fragments1.add(fragBelly);

        Bundle bundleLeg = new Bundle();
        bundleLeg.putStringArray("list",strLeg);
        fragLeg = new MyListFragment();
        fragLeg.setArguments(bundleLeg);
        fragments1.add(fragLeg);

        adapter1 = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        adapter1.addTitlesAndFragments(mTitles1, fragments1);
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