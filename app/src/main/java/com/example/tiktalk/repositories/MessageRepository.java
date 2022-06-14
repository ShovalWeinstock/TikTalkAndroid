package com.example.tiktalk.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.api.ContactAPI;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;

import java.util.List;


public class MessageRepository {
    private ContactDao contactDao;
    // contacts local DB
    private com.example.tiktalk.repositories.ContactListData contactListData;
    private ContactRepository contactRepository;
    private MessageListData messageListData;
    private ContactAPI api;
    private AppDB db;

    public MessageRepository() {
        contactRepository = ContactRepository.getContactRepository();
        db = contactRepository.getDb();
        contactDao = contactRepository.getContactDao();
        contactListData = contactRepository.getContactListData();
        messageListData = new MessageListData();
        api = contactRepository.getApi();
        //api = new ContactAPI(); // no dao
    }

    class MessageListData extends MutableLiveData<List<Message>> {

        public MessageListData() {
            super();
            // local database
            //not hard coded:
            List<Message> messages = contactDao.get(LoggedInUser.currentContact.getId())
                    .getChatWithContact();
            setValue(messages);
        }

        @Override
        protected void onActive() {
            super.onActive();
            //change the db to the one containing the info from the server
            db = AppDB.getDatabase(MyApplication.context);
            contactDao = db.contactDao();
            // update the mutable live data
            messageListData.postValue(contactDao
                    .get(LoggedInUser.getUsername())
                    .getChatWithContact());
            new Thread(() -> {
                //sleep for emphasis the communication with the server
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // not local database
                api.getChat();
            }).start();
        }
    }

    public LiveData<List<Message>> getChat() {
        return messageListData;
    }
    //.getValue().get(contactListData.getValue().indexOf(LoggedInUser.currentContact.getId())).getChatWithContact()
//    public void add(final Contact contact) {
//        contactDao.insert(contact);
//        api.add(contact);
//    }

    public void reload() {
        api.get();
    }
}