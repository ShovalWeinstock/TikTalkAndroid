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


public class ContactRepository {
    private ContactDao contactDao;
    // contacts local DB
    private ContactListData contactListData;
    private ContactAPI api;
    private AppDB db;
    private static volatile ContactRepository repository;


    public static ContactRepository getContactRepository() {
        if(repository == null){
            repository =  new ContactRepository();
        }
        return repository;
    }

    public ContactRepository() {
        db = AppDB.getDatabase(MyApplication.context);
        contactDao = db.contactDao();
        contactListData = new ContactListData(contactDao,api, db);
        api = new ContactAPI(contactListData, contactDao);
        //api = new ContactAPI(); // no dao
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }
//.getValue().get(contactListData.getValue().indexOf(LoggedInUser.currentContact.getId())).getChatWithContact()
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

    public ContactDao getContactDao() {
        return contactDao;
    }
    public ContactListData getContactListData() {
        return contactListData;
    }
    public ContactAPI getApi() {
        return api;
    }
    public AppDB getDb() {
        return db;
    }
}