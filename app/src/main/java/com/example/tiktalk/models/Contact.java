package com.example.tiktalk.models;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
//import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;
    private String name;
    private String last;
    private String lastdate;
    private String server;
    private List<Message> chatWithContact;

//    public Contact(String id, String name, String last,
//                   String lastDate, String server, ArrayList<Message> chatWithContact) {
//        this.id = id;
//        this.name = name;
//        this.last = last;
//        this.lastdate = lastDate;
//        this.server = server;
//        this.chatWithContact = chatWithContact;
//    }
    public Contact(String id, String name, String last,
                   String lastDate, String server) {
        this.id = id;
        this.name = name;
        this.last = last;
        this.lastdate = lastDate;
        this.server = server;
        this.chatWithContact = new ArrayList<>();
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

    public List<Message> getChatWithContact() { return chatWithContact; }

    public void setChatWithContact(List<Message> chatWithContact) {
        this.chatWithContact = chatWithContact;
    }
    @Override
    public String toString() {
        return  ", name='" + name + '\'' +
                ", last='" + last + '\'' +
                ", lastDate='" + lastdate + '\'' +
                '}';
    }

    public static class Converters {
        @TypeConverter
        public static List<Message> fromString(String value) {
            Type listType = new TypeToken<List<Message>>() {}.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public static String fromList(List<Message> list) {
            Gson gson = new Gson();
            return gson.toJson(list);
        }
    }
}
