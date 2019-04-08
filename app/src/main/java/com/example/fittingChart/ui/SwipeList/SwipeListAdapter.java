package com.example.fittingChart.ui.SwipeList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.fittingChart.R;
import com.example.fittingChart.activity.MainActivity;

public class SwipeListAdapter extends BaseAdapter {

    private List<String> list;// = new ArrayList<String>(15);
    Set<SwipeListLayout> sets;
    Context mContext;

    public SwipeListAdapter(Context context, List<String> list, Set<SwipeListLayout> sets){
        this.mContext = context;
        this.list = list;
        this.sets = sets;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View view, ViewGroup arg2) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(
                    R.layout.slip_item_layout, null);
        }
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_name.setText(list.get(arg0));
        final SwipeListLayout sll_main = (SwipeListLayout) view.findViewById(R.id.sll_main);
        TextView tv_top = view.findViewById(R.id.tv_top);
        TextView tv_delete = view.findViewById(R.id.tv_delete);
        sll_main.setOnSwipeStatusListener(new MyOnSlipeStatusListener(sll_main,sets));
        tv_top.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                sll_main.setStatus(SwipeListLayout.Status.Close, true);
                String str = list.get(arg0);
                list.remove(arg0);
                list.add(0, str);
                notifyDataSetChanged();
            }
        });
        tv_delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                sll_main.setStatus(SwipeListLayout.Status.Close, true);
                list.remove(arg0);
                notifyDataSetChanged();
            }
        });
        return view;
    }

}
