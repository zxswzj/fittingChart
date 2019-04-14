package com.example.fittingChart.module;

public class FittingSwipeItemData extends FittingData{
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
