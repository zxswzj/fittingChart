package com.example.fittingChart.module;

public class FittingData {
    Integer id;
    Integer number;
    long time;


    // Empty constructor
    public FittingData(){

    }
    // constructor
    public FittingData(int id, int number, long time){
        this.id = id;
        this.number = number;
        this.time = time;
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

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime(){
        return time;
    }

}
