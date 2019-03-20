package com.example.fittingChart.ui.TabLayout;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    String TAG = "TabLayout";
    private String []titles;
    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.i(TAG, "MyFragmentPagerAdapter.MyFragmentPagerAdapter");
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.i(TAG, "MyFragmentPagerAdapter.destroyItem");

        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.i(TAG, "MyFragmentPagerAdapter.instantiateItem");
        //container.addView(views.get(position));

        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.i(TAG, "MyFragmentPagerAdapter.getPageTitle");
        return titles[position];
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Log.i(TAG, "MyFragmentPagerAdapter.isViewFromObject");

        return super.isViewFromObject(view, object);
    }

    //自定义一个添加title和fragment的方法，供Activity使用
    public void addTitlesAndFragments(String []titles, List<Fragment> fragments) {
        Log.i(TAG, "MyFragmentPagerAdapter.addTitlesAndFragments");

        this.titles = titles;
        this.fragments = fragments;
    }

}


