package com.example.fittingChart.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FittingItem {
//    Integer id;
    @Id
    String name;
    Integer number;
    long durationTime;
    long restTime;
    long localTime;
    String des;
    String unit;    //单位
    Integer set;    //第几组
    Integer toolNumber;  //器械数值，比如使用5kg的哑铃，则为5
    String toolUnit;    //器械的单位，比如使用5kg的哑铃，则为kg


    @Generated(hash = 651548569)
    public FittingItem(String name, Integer number, long durationTime,
            long restTime, long localTime, String des, String unit, Integer set,
            Integer toolNumber, String toolUnit) {
        this.name = name;
        this.number = number;
        this.durationTime = durationTime;
        this.restTime = restTime;
        this.localTime = localTime;
        this.des = des;
        this.unit = unit;
        this.set = set;
        this.toolNumber = toolNumber;
        this.toolUnit = toolUnit;
    }
    @Generated(hash = 2127733768)
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Integer getSet() {
        return this.set;
    }
    public void setSet(Integer set) {
        this.set = set;
    }
    public Integer getToolNumber() {
        return this.toolNumber;
    }
    public void setToolNumber(Integer toolNumber) {
        this.toolNumber = toolNumber;
    }
    public String getToolUnit() {
        return this.toolUnit;
    }
    public void setToolUnit(String toolUnit) {
        this.toolUnit = toolUnit;
    }
}
