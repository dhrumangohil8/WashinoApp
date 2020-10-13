package com.dhruman.washino;

import java.util.List;

public class User {

    private String userName;
    private String userGender;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userCarName;
    private String userCarType;
    private String userCarNumber;
    private String userDefaultTime;

    public User() {

    }

    public User(List<String> list)
    {
        this.userName=list.get(0);
        this.userGender=list.get(1);
        this.userEmail=list.get(2);
        this.userPhone=list.get(3);
        this.userAddress=list.get(4);
        this.userCarName=list.get(5);
        this.userCarNumber=list.get(6);
        this.userCarType=list.get(7);
        this.userDefaultTime=list.get(8);
    }

    public String getUserName(){return userName;}
    public String getUserGender(){return userGender;}
    public String getUserEmail(){return userEmail;}
    public String getUserPhone(){return userPhone;}
    public String getUserAddress(){return userAddress;}
    public String getUserCarName(){return userCarName;}
    public String getUserCarType(){return userCarType;}
    public String getUserCarNumber(){return userCarNumber;}
    public String getUserDefaultTime(){return userDefaultTime;}

}
