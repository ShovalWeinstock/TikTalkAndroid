package com.example.tiktalk.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private int id;
    private String created;
    private boolean sent;
    private String content;
    private String chatWith;

    public Message(String created, boolean sent, String content) {
        this.created = created;
        this.sent = sent;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }
}
