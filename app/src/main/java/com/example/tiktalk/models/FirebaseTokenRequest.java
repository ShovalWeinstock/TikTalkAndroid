package com.example.tiktalk.models;

public class FirebaseTokenRequest {
    String userId;
    String token;


    public String getUserId() {
        return userId;
    }

    public FirebaseTokenRequest(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
