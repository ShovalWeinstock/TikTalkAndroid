package com.example.tiktalk;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.User;

@Database(entities = {Contact.class, User.class}, version = 10)
//            @TypeConverters({Contact.Converters.class}
//            )

//todo: not truly singletone - need to make everything private and add creation method
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract UserDao userDao();
    private static volatile AppDB database;

    public static AppDB getDatabase(final Context context) {
        if(database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "TikTalkDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
