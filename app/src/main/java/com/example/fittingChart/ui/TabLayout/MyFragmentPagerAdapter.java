package com.example.fittingChart.ui.TabLayout;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String []titles;
    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
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
        return titles[position];
    }

    //自定义一个添加title和fragment的方法，供Activity使用
    public void addTitlesAndFragments(String []titles, List<Fragment> fragments) {
        this.titles = titles;
        this.fragments = fragments;
    }
}


