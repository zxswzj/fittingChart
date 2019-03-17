package com.example.fittingChart.ui.TabLayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fittingChart.R;

/**
 * A simple {@link Fragment} subclass.
 */

import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/28.
 */

public class DataFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private TextView textView;
    Button bt;

    public static DataFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        DataFragment fragment = new DataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);

        return rootView;
    }
}

