package com.example.tiktalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class},
            version = 4)
//autoMigrations = {@AutoMigration(from = 3, to = 4)},
//            exportSchema = true)

public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
}
