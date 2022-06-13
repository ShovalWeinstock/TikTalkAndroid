package com.example.tiktalk.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.api.ContactAPI;
import com.example.tiktalk.models.Contact;

import java.util.List;


public class ContactRepository {
    private ContactDao contactDao;
    // contacts local DB
    private ContactListData contactListData;
    private ContactAPI api;
    private AppDB db;

    public ContactRepository() {
        db = AppDB.getDatabase(MyApplication.context);
        contactDao = db.contactDao();
        contactListData = new ContactListData();
        api = new ContactAPI(contactListData, contactDao);
        //api = new ContactAPI(); // no dao
    }

    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData() {
            super();
            // local database
            //not hard coded:
            List<Contact> contacts = contactDao.index();
            setValue(contacts);
        }

        @Override
        protected void onActive() {
            super.onActive();
            //change the db to the one containing the info from the server
            db = AppDB.getDatabase(MyApplication.context);
            contactDao = db.contactDao();
            // update the mutable live data
            contactListData.postValue(contactDao.index());
            new Thread(() -> {
                //sleep for emphasis the communication with the server
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // not local database
                api.get();
            }).start();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add(final Contact contact) {
        contactDao.insert(contact);
        api.add(contact);
    }
    public void update(final Contact contact) {
        contactDao.update(contact);
        api.update(contact);
    }

    public void delete(final Contact contact) {
        contactDao.delete(contact);
        api.delete(contact);
    }

    public void reload() {
        api.get();
    }

}