package com.example.fittingChart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fittingChart.TabLayout.ItemFragment;
import com.example.fittingChart.TabLayout.TabFragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment_activity extends Fragment {
    private View view;//定义view用来设置fragment的layout
    public RecyclerView mCollectRecyclerView;//定义RecyclerView
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
    //自定义recyclerveiw的适配器
    private ActionAdapter mCollectRecyclerAdapter;

    //TabLayout
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取fragment的layout
        view = inflater.inflate(R.layout.fragment_activity, container, false);
        //对recycleview进行配置
        initRecyclerView();
        //模拟数据
        initData();

        //tableLayout
        tabLayout = view.findViewById(R.id.tl_tabs);
        viewPager = view.findViewById(R.id.vp_content);

        fragments.add(TabFragment.newInstance("a","b"));
        fragments.add(TabFragment.newInstance("c","b"));
        fragments.add(TabFragment.newInstance("d","b"));
        fragments.add(TabFragment.newInstance("e","b"));
        fragments.add(TabFragment.newInstance("f","b"));
        titles.add("head");
        titles.add("breast");
        titles.add("back");
        titles.add("shoulder");
        titles.add("arm");

        viewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.i("Fragment", "Fragment_activity.getItem");

                return fragments.get(position);
            }

            @Override
            public int getCount() {
                Log.i("Fragment", "Fragment_activity.getCount");

                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
                Log.i("Fragment", "Fragment_activity.destroyItem");

            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                Log.i("Fragment", "Fragment_activity.getPageTitle");
                return titles.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    /**
     * TODO 模拟数据
     */
    private void initData() {
        Log.i("Fragment", "Fragment_activity.initData");
        for (int i=0;i<10;i++){
            GoodsEntity goodsEntity=new GoodsEntity();
            goodsEntity.setGoodsName("模拟数据"+i);
            goodsEntity.setGoodsPrice("100"+i);
            goodsEntityList.add(goodsEntity);
        }
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.collect_recyclerView);
        //创建adapter
        mCollectRecyclerAdapter = new ActionAdapter(getActivity(), goodsEntityList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new ActionAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, GoodsEntity data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(),"我是item",Toast.LENGTH_SHORT).show();
            }
        });
    }

}