package com.example.fittingChart.database;

import android.widget.ImageView;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class FittingTable {

    @Id
    String name;
    String dbName;
    String des;
    int layoutResourceID;

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
