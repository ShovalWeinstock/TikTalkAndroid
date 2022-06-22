package com.example.tiktalk;

import com.example.tiktalk.models.Contact;

public class LoggedInUser {
    static String username;
    static String nickname;
    static String server = "5051";
    static Contact currentContact;

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        LoggedInUser.server = server;
    }

    public static Contact getCurrentContact() {
        return currentContact;
    }

    public static void setCurrentContact(Contact currentContact) {
        LoggedInUser.currentContact = currentContact;
    }

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


