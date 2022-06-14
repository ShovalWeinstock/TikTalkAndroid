package com.example.tiktalk;

public class Message {

    private int id;
    private String created;
    private boolean sent;
    private String content;

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
}
