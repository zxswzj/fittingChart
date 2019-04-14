package com.example.fittingChart.module;

public class FittingData {
//    Integer id;
    Integer number;
    long durationTime;
    long restTime;
    long localTime;
    String des;


    // Empty constructor
    public FittingData(){

    }
    // constructor
    public FittingData(int number, long durationTime, long restTime, long localTime, String des){
//        this.id = id;
        this.number = number;
        this.durationTime = durationTime;
        this.restTime = restTime;
        this.localTime = localTime;
        this.des = des;
    }

    public String getDes(){
        return this.des;
    }
    public void setDes(String des){
        this.des = des;
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

    public void setRestTime(long time) {
        this.restTime = time;
    }

    public long getRestTime(){
        return restTime;
    }

    public void setLocalTime(long time) {
        this.localTime = time;
    }

    public long getLocalTime(){
        return localTime;
    }
}
