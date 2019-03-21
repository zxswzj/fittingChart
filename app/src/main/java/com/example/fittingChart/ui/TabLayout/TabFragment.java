package com.example.fittingChart.ui.TabLayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fittingChart.GoodsEntity;
import com.example.fittingChart.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;//定义view用来设置fragment的layout
    public RecyclerView recyclerView;//定义RecyclerView
    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
    //private ActionAdapter actionAdapter;

    public TabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment newInstance(String param1, String param2) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "TabFragment.onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Fragment", "TabFragment.onCreateView");
        view = inflater.inflate(R.layout.fragment_tab, container, false);

        //init RecyclerView
//        recyclerView = view.findViewById(R.id.frag_recyclerView);
//        actionAdapter = new ActionAdapter(getContext(), goodsEntityList);
//        recyclerView.setAdapter(actionAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
//        actionAdapter.setOnItemClickListener(new ActionAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, GoodsEntity data) {
//                Toast.makeText(getActivity(),"TabFragment.recyclerView",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Log.i("Fragment", "Fragment_activity.initData");
//        for (int i=0;i<3;i++){
//            GoodsEntity goodsEntity=new GoodsEntity();
//            goodsEntity.setGoodsName("俯卧撑"+i);
//            goodsEntity.setGoodsPrice("10"+i);
//            goodsEntityList.add(goodsEntity);
//        }
        return view;
    }

}
