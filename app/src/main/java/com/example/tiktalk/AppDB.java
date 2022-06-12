package com.example.tiktalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Contact.class},
            version = 7)
@TypeConverters({Contact.Converters.class})
//autoMigrations = {@AutoMigration(from = 3, to = 4)},
//            exportSchema = true)

public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
}
