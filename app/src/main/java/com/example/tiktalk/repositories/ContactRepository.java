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
    }

    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData() {
            super();
            // local database
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
                // not local database
                api.get();
            }).start();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add(final Contact contact) {
        // add contact to local db
        contactDao.insert(contact);
        // add contact to remote db
        api.add(contact);
    }
    public void update(final Contact contact) {
        // update contact on local db
        contactDao.update(contact);
        // update contact on remote db
        api.update(contact);
    }

    public void delete(final Contact contact) {
        // delete contact from local db
        contactDao.delete(contact);
        // delete contact from remote db
        api.delete(contact);
    }

    public void reload() {
        api.get();
    }
}