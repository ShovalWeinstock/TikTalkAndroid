package com.example.tiktalk.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String id; //username
    private String Name; //nickname
    //private android.graphics.drawable.Drawable profilePic;
    private String password;

    public User(String id, String name, String password) {
        this.id = id;
        Name = name;
        this.password = password;
    }

    public User() {
        id = "";
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
