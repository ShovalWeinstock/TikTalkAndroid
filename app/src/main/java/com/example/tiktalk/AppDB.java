package com.example.tiktalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, User.class}, version = 5)
//autoMigrations = {@AutoMigration(from = 3, to = 4)},
//            exportSchema = true)

public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract UserDao userDao();
}
