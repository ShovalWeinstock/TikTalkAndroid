package com.example.tiktalk;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.models.User;


// local database
@Database(entities = {Contact.class, User.class, Message.class}, version = 16)

public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();
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
