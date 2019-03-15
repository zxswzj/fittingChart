package com.example.fittingChart;


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
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.TabLayout.BlankFragment;
import com.example.fittingChart.TabLayout.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {

    View view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Fragment", "FragmentActivity.onActivityCreated");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Fragment", "FragmentActivity.onAttach");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Fragment", "FragmentActivity.onResume");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Fragment", "FragmentActivity.onStart");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment", "FragmentActivity.onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Fragment", "FragmentActivity.onStop");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Fragment", "FragmentActivity.onDetach");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Fragment", "FragmentActivity.onDestroyView");

    }

    private String [] mTitles1 = {"胸", "肩", "手臂","背"};
    private String [] mTitles2 = {"腹","腿","大脑","???"};
    TabLayout tabLayout1,tabLayout2;
    ViewPager viewPager1, viewPager2;
    List<Fragment> fragments1 = new ArrayList<>();
    List<Fragment> fragments2 = new ArrayList<>();
    MyFragmentPagerAdapter adapter1;
    MyFragmentPagerAdapter adapter2;

    public ActivityFragment() {
        // Required empty public constructor
        Log.i("Fragment", "FragmentActivity.FragmentActivity");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Fragment", "FragmentActivity.onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment, container, false);
        tabLayout1 = view.findViewById(R.id.tl_tab1);
        tabLayout2 = view.findViewById(R.id.tl_tab2);
        viewPager1 = view.findViewById(R.id.vp_content);
        viewPager2 = view.findViewById(R.id.vp_content2);
        fragments1.add(new BlankFragment());
        fragments1.add(new BlankFragment());
        fragments1.add(new BlankFragment());
        fragments1.add(new BlankFragment());
        fragments2.add(new BlankFragment());
        fragments2.add(new BlankFragment());
        fragments2.add(new BlankFragment());
        fragments2.add(new BlankFragment());


        adapter1 = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        adapter1.addTitlesAndFragments(mTitles1, fragments1);
        adapter2 = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        adapter2.addTitlesAndFragments(mTitles2, fragments2);

        viewPager1.setAdapter(adapter1); // 给ViewPager设置适配器
        tabLayout1.setupWithViewPager(viewPager1); //关联TabLayout和ViewPager
        viewPager2.setAdapter(adapter2); // 给ViewPager设置适配器
        tabLayout2.setupWithViewPager(viewPager2); //关联TabLayout和ViewPager

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("Fragment", "ActivityFragment.onTabSelected");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //setContentView(tabLayout);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "FragmentActivity.onCreate");

    }


}
