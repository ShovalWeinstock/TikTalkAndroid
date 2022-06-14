package com.example.tiktalk;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("SELECT * FROM contact WHERE id = :id")
    Contact get(String id);

    @Query("DELETE FROM contact")
    void clear();

//    @Query("INSERT :chat TO contacts.")
//    void addChatToContact(List<Message> chat, String contactId) {
//
//
//    }

    @Insert
    void insertList(List<Contact> list);

    @Insert
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);


}


