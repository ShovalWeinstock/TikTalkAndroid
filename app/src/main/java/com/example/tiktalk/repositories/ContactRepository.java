package com.example.tiktalk.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.Contact;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.api.ContactAPI;

import java.util.ArrayList;
import java.util.List;


public class ContactRepository {
    private ContactDao dao;
    // contacts local DB
    private ContactListData contactListData;
    private ContactAPI api;

    public ContactRepository() {
        AppDB db = AppDB.getDatabase(MyApplication.context);
        dao = db.contactDao();
        contactListData = new ContactListData();
        api = new ContactAPI(contactListData, dao);
        //api = new ContactAPI(); // no dao
    }

    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData() {
            super();
            // local database
            //not hard coded:
            List<Contact> contacts = dao.index();
            // hard coded (no dao):
            //List<Contact> contacts = new ArrayList<>();
            //contacts.add(new Contact("shoval", "shov", "hi", "5/5/5", "localhost:555"));
            setValue(contacts);
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                contactListData.postValue(dao.index());
            }).start();

            // not local database
            //ContactAPI contactsApi = new ContactAPI();
            //contactsApi.get();
            api.get();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add(final Contact contact) {
        api.add(contact);
    }

    public void delete(final Contact contact) {
        api.delete(contact);
    }

    public void reload() {
        api.get();
    }

}