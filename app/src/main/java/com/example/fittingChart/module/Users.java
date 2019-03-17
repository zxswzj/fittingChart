package com.example.fittingChart;

public class Users {
    Integer id;
    String username;
    String slogan;
    private  int imgID;


    // Empty constructor
    public Users(){

    }
    // constructor
    public Users(int id, String name, String slogan, int imgID){
        this.id = id;
        this.username = name;
        this.slogan = slogan;
        this.imgID = imgID;
    }

    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
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
}
