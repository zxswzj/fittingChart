package com.example.fittingChart.ui.CustomViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fittingChart.module.ModelObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anupamchugh on 26/12/15.
 */
public class CustomPagerAdapter extends PagerAdapter {

    String TAG = "PagerAdapter";
    private Context mContext;
    private List<View> listViews = new ArrayList<>();
    private String[] tabLayoutName;

    public CustomPagerAdapter(Context context, List<View> listviews, String[] tabLayoutName) {
        this.listViews = listviews;
        this.mContext = context;
        this.tabLayoutName = tabLayoutName;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
//        ModelObject modelObject = ModelObject.values()[position];
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);
//        collection.addView(layout);
        try {
            if(listViews.get(position).getParent()==null)
                collection.addView(listViews.get(position), 0);
            else {
                ((ViewGroup)listViews.get(position).getParent()).removeView(listViews.get(position));
                collection.addView(listViews.get(position), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listViews.get(position);
//        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
//        collection.removeView((View) view);
        collection.removeView(listViews.get(position));
    }

    @Override
    public int getCount() {
        return listViews.size();
        //return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.i(TAG, "PagerAdapter.getPageTitle:"+position);

        //listViews.get(position)
//        ModelObject customPagerEnum = ModelObject.values()[position];
//        return mContext.getString(customPagerEnum.getTitleResId());
        return tabLayoutName[position];
    }

}
