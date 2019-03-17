package com.example.fittingChart.module;

public class FittingData {
    Integer id;
    Integer number;
    String time;


    // Empty constructor
    public FittingData(){

    }
    // constructor
    public FittingData(int id, int number, String time){
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

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime(){
        return time;
    }

}
