package com.example.tiktalk;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("DELETE FROM message WHERE chatWith = :contactId")
    void clearByContact(String contactId);

    @Query("SELECT * FROM message WHERE chatWith = :contactId")
    List<Message> getChatWithContact(String contactId);

    @Insert
    void insertList(List<Message> list);

    @Insert
    void insert(Message... messages);

    @Query("DELETE FROM contact")
    void clear();

}
