package com.example.fittingChart;

import android.content.Intent;
import android.support.v4.app.ListFragment;

import android.widget.ListView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.widget.Toast;
import android.widget.SimpleAdapter;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MyListFragment extends ListFragment{
    private static final String TAG = "MyListFragment";


    String[] strs = {
            "listItem1",
            "listItem2",
            "listItem3"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "MyListFragment.onCreateView");
        Bundle bundle = getArguments();
        if(bundle != null)
            strs = bundle.getStringArray("list");

        // 设置ListFragment默认的ListView，即@id/android:list
        this.setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, strs));
        return inflater.inflate(R.layout.mylist_fragment, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "MyListFragment.onCreate");
        super.onCreate(savedInstanceState);

    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id) {
        Log.i(TAG, "MyListFragment.onListItemClick");
        // 找到ListFragmentSelf
//        ListFragmentSelf listFragmentSelf = (ListFragmentSelf) getFragmentManager().
//                findFragmentByTag("frgSelf");
//        listFragmentSelf.flushData(position);

        Intent i = new Intent(getActivity() , FittingTimerActivity.class);
        startActivity(i);
    }

}
