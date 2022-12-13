package com.example.healthyliving;

import android.app.Application;

public class UserInfo extends Application {
    public String UserID;
    public String email;
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public UserInfo(){

    }
    public UserInfo(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return UserID;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setUserID(String  userID) {
        this.UserID = userID;
    }

}
