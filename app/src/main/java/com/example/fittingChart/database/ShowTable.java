package com.example.fittingChart.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShowTable {
    @Id
    String name;
    String DbName;
    int count;

    @Generated(hash = 677839429)
    public ShowTable(String name, String DbName, int count) {
        this.name = name;
        this.DbName = DbName;
        this.count = count;
    }
    @Generated(hash = 952565904)
    public ShowTable() {
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getDbName(){return DbName;}
    public void setDbName(String DBName){this.DbName = DBName;}
    public int getCount(){return count;}
    public void setCount(int count){this.count = count;}
}
