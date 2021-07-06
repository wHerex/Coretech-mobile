package com.example.myapplication.model;

import com.example.myapplication.model.Status;
import com.example.myapplication.model.User;

public class MyCallback {

    private User user;

    private Status status;

    public MyCallback(User user, Status status) {
        this.user = user;
        this.status = status;
    }

    public MyCallback(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

