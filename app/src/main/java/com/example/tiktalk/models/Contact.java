package com.example.tiktalk.models;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
//import com.google.gson.Gson;

import java.util.ArrayList;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;
    private String name;
    private String last;
    private String lastdate;
    private String server;

    public Contact(String id, String name, String last, String lastDate, String server) {
        this.id = id;
        this.name = name;
        this.last = last;
        this.lastdate = lastDate;
        this.server = server;
    }

    public Contact() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getServer() { return server;}

    public void setServer(String server) { this.server = server;}

    @Override
    public String toString() {
        return  ", name='" + name + '\'' +
                ", last='" + last + '\'' +
                ", lastDate='" + lastdate + '\'' +
                '}';
    }

//    public static class Converters {
//        @TypeConverter
//        public static ArrayList<Message> fromString(String value) {
//            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
//            return new Gson().fromJson(value, listType);
//        }
//
//        @TypeConverter
//        public static String fromArrayList(ArrayList<Message> list) {
//            Gson gson = new Gson();
//            return gson.toJson(list);
//        }
//    }
}
