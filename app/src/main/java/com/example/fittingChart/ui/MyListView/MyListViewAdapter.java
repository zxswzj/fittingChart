package com.example.fittingChart.ui.MyListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fittingChart.R;
import com.example.fittingChart.module.FittingTableData;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

    private List<FittingTableData> fittingListItemDataList;// = new ArrayList<>();
    private LayoutInflater inflater; //得到一个LayoutInfalter对象用来导入布局

    public MyListViewAdapter(Context context, List<FittingTableData> fittingListItemDataList) {
        this.inflater = LayoutInflater.from(context);
        this.fittingListItemDataList = fittingListItemDataList;
    }

    @Override
    public int getCount() {
        return fittingListItemDataList == null?0:fittingListItemDataList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.fitting_list_item,null);
        FittingTableData fittingListItemData = (FittingTableData) getItem(position);

        //在view 视图中查找 组件
        TextView tv_name = (TextView) view.findViewById(R.id.fitting_list_item_tv_name);
        TextView tv_age = (TextView) view.findViewById(R.id.fitting_list_item_tv_des);
        ImageView im_photo = (ImageView) view.findViewById(R.id.fitting_list_item_iv);

        //为Item 里面的组件设置相应的数据
        tv_name.setText(fittingListItemData.getName());
        tv_age.setText(fittingListItemData.getDes());
        im_photo.setImageResource(fittingListItemData.getResourceID());

        //返回含有数据的view
        return view;
    }

    @Override
    public Object getItem(int position) {
        return fittingListItemDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

