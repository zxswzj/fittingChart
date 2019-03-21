package com.example.fittingChart.module;

import android.widget.ImageView;

public class FittingListItemData {

    public FittingListItemData(String name, String des, int imageViewID){
        this.name = name;
        this.des = des;
        this.layoutResourceID = imageViewID;
    }
    String name;
    String des;
    int layoutResourceID;

    public String getName(){
        return name;
    }
    public String getDes(){
        return des;
    }
    public int getResourceID(){
        return layoutResourceID;
    }
}
