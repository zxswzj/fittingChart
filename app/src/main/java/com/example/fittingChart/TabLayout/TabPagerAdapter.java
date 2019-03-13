package com.example.fittingChart.TabLayout;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fittingChart.TabLayout.ItemFragment;


/**
 * Created by Gulbala on 03.06.2017.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ItemFragment tab1 = new ItemFragment();
                return tab1;
            case 1:
                ItemFragment tab2 = new ItemFragment();
                return tab2;
            case 2:
                ItemFragment tab3 = new ItemFragment();
                return tab3;
            case 3:
                ItemFragment tab4 = new ItemFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}