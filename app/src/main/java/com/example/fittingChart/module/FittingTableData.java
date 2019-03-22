package com.example.fittingChart.module;

import android.widget.ImageView;

//描述数据库中的项目表
public class FittingTableData {

    int idx = 0;
    String name;
    String dbName;
    String des;
    int layoutResourceID;

    public FittingTableData(){}

    public FittingTableData(String name, String dbName, String des, int layoutResourceID){
        this.name = name;
        this.dbName = dbName;
        this.des = des;
        this.layoutResourceID = layoutResourceID;
    }

    public void setDbName(String dbName){ this.dbName = dbName; }
    public void setName(String name){
        this.name = name;
    }
    public void setDes(String des){
        this.des = des;
    }
    public void setResourceID(int layoutResourceID){
        this.layoutResourceID = layoutResourceID;
    }
    public String getDbName(){ return dbName; }
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
