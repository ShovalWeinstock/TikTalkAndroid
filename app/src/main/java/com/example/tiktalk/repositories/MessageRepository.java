package com.example.tiktalk.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.tiktalk.AppDB;
import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.MessageDao;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.api.MessageAPI;
import com.example.tiktalk.models.Message;

import java.util.List;

public class MessageRepository {
    private MessageDao messageDao;
    // messages local DB
    private MessageRepository.MessageListData messageListData;
    private MessageAPI api;
    private AppDB db;

    public MessageRepository() {
        db = AppDB.getDatabase(MyApplication.context);
        messageDao = db.messageDao();
        messageListData = new MessageRepository.MessageListData();
        api = new MessageAPI(messageListData, messageDao);
    }

    class MessageListData extends MutableLiveData<List<Message>> {

        public MessageListData() {
            super();
            // local chat with current contact
            List<Message> messages = messageDao.getChatWithContact(LoggedInUser.getCurrentContact().getId());
            setValue(messages);
        }

        @Override
        protected void onActive() {
            super.onActive();
            //change the db to the one containing the info from the server
            db = AppDB.getDatabase(MyApplication.context);
            messageDao = db.messageDao();
            // update the mutable live data
            messageListData.postValue(messageDao.getChatWithContact(
                                      LoggedInUser.getCurrentContact().getId()));
            new Thread(() -> {
                api.getChat();
            }).start();
        }
    }

    public LiveData<List<Message>> getCurrentChat() {
        return messageListData;
    }

    public void add(final Message message) {
        api.addMessageToServer(message);
    }

    public void reload() {
        api.getChat();
    }

}
