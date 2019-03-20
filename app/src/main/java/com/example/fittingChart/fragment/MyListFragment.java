package com.example.fittingChart.fragment;

import android.content.Intent;
import android.support.v4.app.ListFragment;

import android.widget.ListView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.util.Log;

import com.example.fittingChart.R;
import com.example.fittingChart.activity.FittingActivity;

public class MyListFragment extends ListFragment{
    private static final String TAG = "TabLayout";

    private ArrayAdapter<String> adapter;

    String[] strs;
    String[] str1={"aa","bb","cc"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "MyListFragment.onCreateView");

        return inflater.inflate(R.layout.mylist_fragment, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "MyListFragment.onCreate");
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null)
            strs = bundle.getStringArray("list");
        else
            Log.i(TAG, "MyListFragment.onCreate: empty list!!!");

        // 设置ListFragment默认的ListView，即@id/android:list
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, strs);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id) {
        Log.i(TAG, "MyListFragment.onListItemClick");
        // 找到ListFragmentSelf
//        ListFragmentSelf listFragmentSelf = (ListFragmentSelf) getFragmentManager().
//                findFragmentByTag("frgSelf");
//        listFragmentSelf.flushData(position);

        Intent i = new Intent(getActivity() , FittingActivity.class);
        startActivity(i);
    }

}
