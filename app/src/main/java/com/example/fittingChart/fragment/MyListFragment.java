package com.example.fittingChart.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.database.FittingTable;
import com.example.fittingChart.ui.SwipeList.MySwipeListAdapter;
import com.example.fittingChart.ui.SwipeList.SwipeMenu;
import com.example.fittingChart.ui.SwipeList.SwipeMenuCreator;
import com.example.fittingChart.ui.SwipeList.SwipeMenuItem;
import com.example.fittingChart.ui.SwipeList.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment {

    String TAG = "SwipeList";
    View view;
    ArrayList<FittingTable> mAppList = new ArrayList<>();
    private MySwipeListAdapter mAdapter;
    private SwipeMenuListView mListView;

    public MyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_list, container, false);
        Log.i(TAG, "MyListFragment.onCreateView");


        mListView = view.findViewById(R.id.listView);

        mAdapter = new MySwipeListAdapter(getContext(),mAppList);
        mListView.setAdapter(mAdapter);

        // 第1步：设置创建器，并且在其中生成我们需要的菜单项，将其添加进菜单中
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // 创建“打开”项
                SwipeMenuItem openItem = new SwipeMenuItem(getContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9,0xC9,0xCE)));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("查看");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(openItem);

                // 创建“删除”项
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.icon_add);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(deleteItem);
            }
        };
        // 为ListView设置创建器
        mListView.setMenuCreator(creator);

        // 第2步：为ListView设置菜单项点击监听器，来监听菜单项的点击事件
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //position:列表项的下标。如：0，1，2，3，4，...
                //index:菜单项的下标。如：0，1，2，3，4，...
                //ApplicationInfo item = mAppList.get(position);
                //String str = mAppList.get(position);
                switch (index) {
                    case 0:
                        // open
                        //open(item);
                        break;
                    case 1:
                        // delete
//                    delete(item);
                        mAppList.remove(position);
                        //通知监听者数据集发生改变，更新ListView界面
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                // true：其他已打开的列表项的菜单状态将保持原样，不会受到其他列表项的影响而自动收回
                // false:已打开的列表项的菜单将自动收回
                return false;
            }
        });

        // 设置侧滑监听器，监听侧滑开始和侧滑结束
        // 注意：当我们将一个已经侧滑出来的菜单重新收回去的时候并不会调用onSwipeStart方法，
        // 但是结束的时候依然会调用onSwipeEnd方法
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // 设置监听Menu状态改变的监听器（Menu的打开和关闭）
        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });

        // other setting
//        listView.setCloseInterpolator(new BounceInterpolator());

        // 设置列表项长点击的监听器
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return view;
    }

    //删除一个应用，在这里并没有被调用，因为这会卸载相应的app
    private void delete(ApplicationInfo item) {
        try {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.fromParts("package", item.packageName, null));
            startActivity(intent);
        } catch (Exception e) {
        }
    }

    // 打开app
    private void open(ApplicationInfo item) {
        // Intent.ACTION_MAIN:作为主进入点启动，并不期望获得数据
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //根据传进来的ApplicationInfo item设置要解析的应用的包名
        resolveIntent.setPackage(item.packageName);
        //根据指定的Intent解析出对应的应用中所有的activity的信息
        List<ResolveInfo> resolveInfoList = getActivity().getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        if (resolveInfoList != null && resolveInfoList.size() > 0) {
            //ResolveInfo:返回依据IntentFilter解析出来的Intent所返回的信息。
            //此部分对应于从AndroidManifest.xml中<意图>标签收集信息。
            //解析出来的第一个为主activity的信息
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(
                    activityPackageName, className);

            intent.setComponent(componentName);
            startActivity(intent);
        }
    }

    // 将dp转换为px
    private int dp2px(int value) {
        // 第一个参数为我们待转的数据的单位，此处为 dp（dip）
        // 第二个参数为我们待转的数据的值的大小
        // 第三个参数为此次转换使用的显示量度（Metrics），它提供屏幕显示密度（density）和缩放信息
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "MyListFragment.onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "MyListFragment.onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MyListFragment.onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "MyListFragment.onDestroyView");
    }

    //另一种将dp转换为px的方法
}
//    public void onListItemClick(ListView parent, View v,
//                                int position, long id) {
//        Log.i(TAG, "MyListFragment.onListItemClick");
//        // 找到ListFragmentSelf
////        ListFragmentSelf listFragmentSelf = (ListFragmentSelf) getFragmentManager().
////                findFragmentByTag("frgSelf");
////        listFragmentSelf.flushData(position);
//
//        Intent i = new Intent(getActivity() , FittingActivity.class);
//        startActivity(i);
//    }
