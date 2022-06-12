package com.example.tiktalk;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Contact.class, User.class},
            version = 7)
@TypeConverters({Contact.Converters.class})
//autoMigrations = {@AutoMigration(from = 3, to = 4)},
//            exportSchema = true)

public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract UserDao userDao();
    private static volatile AppDB database;

    public static AppDB getDatabase(final Context context) {
        if(database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "TikTalkDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}
