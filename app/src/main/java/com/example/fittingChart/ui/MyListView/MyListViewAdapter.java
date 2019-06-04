package com.example.fittingChart.ui.MyListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fittingChart.R;
import com.example.fittingChart.database.FittingTable;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

    private List<FittingTable> dataList;// = new ArrayList<>();
    private LayoutInflater inflater; //得到一个LayoutInfalter对象用来导入布局

    public MyListViewAdapter(Context context, List<FittingTable> fittingTableDataList) {
        this.inflater = LayoutInflater.from(context);
        this.dataList = fittingTableDataList;
    }

    @Override
    public int getCount() {
        return dataList == null?0:dataList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.fitting_list_item,null);
        FittingTable fittingListItemData = (FittingTable) getItem(position);

        //在view 视图中查找 组件
        TextView tv_name =  view.findViewById(R.id.fitting_list_item_tv_name);
        TextView tv_age =  view.findViewById(R.id.fitting_list_item_tv_des);
        TextView tv_dbName = view.findViewById(R.id.fitting_list_item_tv_dbName);
        ImageView im_photo =  view.findViewById(R.id.fitting_list_item_iv);

        //为Item 里面的组件设置相应的数据
        tv_name.setText(fittingListItemData.getName());
        tv_dbName.setText(fittingListItemData.getDbName());
        tv_age.setText(fittingListItemData.getDes());
        im_photo.setImageResource(fittingListItemData.getResourceID());

        //返回含有数据的view
        return view;
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

