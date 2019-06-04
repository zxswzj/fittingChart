package com.example.fittingChart.database;

public class FittingSwipeItemData extends FittingItem {
    String name;
    int resourceID;

    public void setName(String name){
        this.name = name;
    }
    public void setResourceID(int resourceID){
        this.resourceID = resourceID;
    }
    public String getName(){
        return name;
    }
    public int getResourceID(){
        return resourceID;
    }
}
