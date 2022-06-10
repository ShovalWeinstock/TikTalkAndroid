package com.example.tiktalk;

public class LoggedInUser {
    public static User loggedInUser;

    public static void setLoggedInUser(User loggedInUser) {
        LoggedInUser.loggedInUser = loggedInUser;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
