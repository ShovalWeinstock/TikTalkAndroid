package com.example.tiktalk.models;

public class Transfer {
    private String from;
    private String to;
    private String content;

    public Transfer(String from, String to , String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
}
