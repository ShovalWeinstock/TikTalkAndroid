package com.example.tiktalk;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;
    private String name;
    private String last;
    private String lastDate;
    private String server;
    //private List<Message> chatWithContact;

    public Contact(String id, String name, String last, String lastDate, String server) {
        this.id = id;
        this.name = name;
        this.last = last;
        this.lastDate = lastDate;
        this.server = server;
        //this.chatWithContact = new ArrayList<>();
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

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getServer() { return server;}

    public void setServer(String server) { this.server = server;}

    //public List<Message> getChatWithContact() {return chatWithContact;}

    //public void setChatWithContact(List<Message> chatWithContact) {this.chatWithContact = chatWithContact;}

    @Override
    public String toString() {
        return  "{ name='" + name + '\'' +
                ", last='" + last + '\'' +
                ", lastDate='" + lastDate + '\'' +
                '}';
    }
}