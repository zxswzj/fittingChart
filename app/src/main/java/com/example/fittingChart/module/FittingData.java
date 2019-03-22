package com.example.fittingChart.module;

public class FittingData {
    Integer id;
    Integer number;
    long durationTime;
    long localTime;
    String des;


    // Empty constructor
    public FittingData(){

    }
    // constructor
    public FittingData(int id, int number, long durationTime, long localTime){
        this.id = id;
        this.number = number;
        this.durationTime = durationTime;
        this.localTime = localTime;
    }

    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDurationTime(long time) {
        this.durationTime = time;
    }

    public long getDurationTime(){
        return durationTime;
    }

    public void setLocalTime(long time) {
        this.localTime = time;
    }

    public long getLocalTime(){
        return localTime;
    }
}
