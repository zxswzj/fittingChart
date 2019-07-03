package com.example.fittingChart.database;

import android.widget.ImageView;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FittingTable {

    @Id
    String name;
    String dbName;
    String category;
    String des;
    Integer layoutResourceID;

    @Generated(hash = 1595463647)
    public FittingTable(String name, String dbName, String category, String des,
            Integer layoutResourceID) {
        this.name = name;
        this.dbName = dbName;
        this.category = category;
        this.des = des;
        this.layoutResourceID = layoutResourceID;
    }
    @Generated(hash = 1015679048)
    public FittingTable() {
    }

    public void setDbName(String dbName){ this.dbName = dbName; }
    public void setName(String name){
        this.name = name;
    }
    public void setDes(String des){
        this.des = des;
    }
    public String getDbName(){ return dbName; }
    public String getName(){
        return name;
    }
    public String getDes(){
        return des;
    }
    public Integer getLayoutResourceID() {
        return this.layoutResourceID;
    }
    public void setLayoutResourceID(Integer layoutResourceID) {
        this.layoutResourceID = layoutResourceID;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
