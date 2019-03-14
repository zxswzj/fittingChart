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

    private String [] mTitles = {"胸", "肩", "手臂","背","腹","腿","大脑"};
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();
    MyFragmentPagerAdapter adapter;

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
        tabLayout = view.findViewById(R.id.tl_tabs);
        viewPager = view.findViewById(R.id.vp_content);

        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());


        adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addTitlesAndFragments(mTitles, fragments);

        viewPager.setAdapter(adapter); // 给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager); //关联TabLayout和ViewPager

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "FragmentActivity.onCreate");

    }
}
