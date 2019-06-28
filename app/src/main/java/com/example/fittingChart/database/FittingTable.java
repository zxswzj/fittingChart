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
    String des;
    Long layoutResourceID;

    @Generated(hash = 1727028171)
    public FittingTable(String name, String dbName, String des,
            Long layoutResourceID) {
        this.name = name;
        this.dbName = dbName;
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
    public void setResourceID(Long layoutResourceID){
        this.layoutResourceID = layoutResourceID;
    }
    public String getDbName(){ return dbName; }
    public String getName(){
        return name;
    }
    public String getDes(){
        return des;
    }
    public Long getResourceID(){
        return layoutResourceID;
    }
    public Long getLayoutResourceID() {
        return this.layoutResourceID;
    }
    public void setLayoutResourceID(Long layoutResourceID) {
        this.layoutResourceID = layoutResourceID;
    }
}
