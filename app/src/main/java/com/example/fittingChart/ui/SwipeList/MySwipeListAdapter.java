package com.example.fittingChart.ui.SwipeList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fittingChart.R;
import com.example.fittingChart.database.FittingItem;
import com.example.fittingChart.database.FittingTable;

import java.util.ArrayList;


public class MySwipeListAdapter extends BaseSwipeListAdapter {

    String TAG = "SwipeList";
    //ArrayList<String> mList;
    ArrayList<FittingTable> mFittingTableDataList;
    ArrayList<FittingItem> mFittingDataList;
    Context mContext;
    int dataformat = 0;

    public MySwipeListAdapter(Context context, ArrayList<FittingTable> list){
        this.mContext = context;
        this.mFittingTableDataList = list;
    }

//    public MySwipeListAdapter(Context context, ArrayList<FittingItem> list){
//        this.mContext = context;
//        this.mFittingDataList = list;
//    }

//    public MySwipeListAdapter(Context context, ArrayList<FittingTable> list, int dummy) {
//        this.mContext = context;
//        this.mFittingTableDataList = list;
//    }

    @Override
    public int getCount() {
        Log.i(TAG, "MySwipeListAdapter.getCount: " + mFittingTableDataList.size());
        return mFittingTableDataList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "MySwipeListAdapter.getItem");
        return mFittingTableDataList.get(position);
    }

//    @Override
//    public FittingItem getItem(int position){
//        return mFittingTableDataList.get(position);
//    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "MySwipeListAdapter.getItemId : " + position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            //夹在金列表项的布局item_list_app.xml
            convertView = View.inflate(mContext, R.layout.item_list_app, null);
            //new MainActivity.AppAdapter.ViewHolder(convertView);

            new ViewHolder(convertView);
        }
        Log.i(TAG, "MySwipeListAdapter.getView: xy=" + convertView.getWidth() + "/" + convertView.getHeight());

        ViewHolder holder = (ViewHolder) convertView.getTag();
        // 获取手机全部应用的信息
//        ApplicationInfo item = getItem(position);
        FittingTable ftd = (FittingTable)getItem(position);
        // 加载应用的图标
//        holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
        holder.iv_icon.setImageResource(ftd.getLayoutResourceID());
        // 加载应用的标题
//        holder.tv_name.setText(item.loadLabel(getPackageManager()));
        holder.tv_name.setText(ftd.getName());
        holder.tv_name.setTag(ftd.getDbName());
        // 为图标设置点击事件监听器（弹出一个toast）
        holder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "iv_icon_click", Toast.LENGTH_SHORT).show();
            }
        });
        // 为应用名字设置点击监听器（弹出一个toast）
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"iv_icon_click",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;

        //根据传进来的convertView创建ViewHolder，并且将其设置为convertView的Tag
        public ViewHolder(View view) {
            iv_icon = view.findViewById(R.id.iv_icon);
            tv_name = view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }

    //这里我们可以根据列表项的位置来设置某项是否允许侧滑
    //(此处我们设置的是当下标为偶数项的时候不允许侧滑)
    @Override
    public boolean getSwipeEnableByPosition(int position) {
//            if(position % 2 == 0){
//                return false;
//            }
        return true;
    }
}