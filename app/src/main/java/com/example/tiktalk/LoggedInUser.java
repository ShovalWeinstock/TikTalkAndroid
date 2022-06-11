package com.example.tiktalk;

public class LoggedInUser {
    public static String username;
    public static String nickname;
    // picture

    public static void setLoggedInUser(String username, String nickname) {
        LoggedInUser.username = username;
        LoggedInUser.nickname = nickname;
    }

    public static void setUsername(String username) {
        LoggedInUser.username = username;
    }
    public static String getUsername() {
        return username;
    }


    public static void setNickname(String nickname) {
        LoggedInUser.nickname = nickname;
    }
    public static String getNickname() {
        return nickname;
    }






}


