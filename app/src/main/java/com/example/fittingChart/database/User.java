package com.example.fittingChart.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id
    //private Long id;
    //@Unique
    String username;
    @Property
    String slogan;
    int imgID;


    @Generated(hash = 941709648)
    public User(String username, String slogan, int imgID) {
        this.username = username;
        this.slogan = slogan;
        this.imgID = imgID;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setSlogan(String str) {
        this.slogan = str;
    }

    public String getSlogan(){
        return slogan;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

//    public Long getId() {
//        return this.id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
}
