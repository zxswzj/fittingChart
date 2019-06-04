package com.example.fittingChart.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FittingItem {
//    Integer id;
    @Id
    Integer number;
    long durationTime;
    long restTime;
    long localTime;
    String des;


    @Generated(hash = 1110836485)
    public FittingItem(Integer number, long durationTime, long restTime, long localTime, String des) {
        this.number = number;
        this.durationTime = durationTime;
        this.restTime = restTime;
        this.localTime = localTime;
        this.des = des;
    }

    @Generated(hash = 893068348)
    public FittingItem() {
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
    public void setNumber(Integer number) {
        this.number = number;
    }
}
