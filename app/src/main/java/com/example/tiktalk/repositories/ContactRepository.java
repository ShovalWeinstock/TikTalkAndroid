package com.example.tiktalk.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.Contact;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.api.ContactAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {
    private ContactDao dao;
    private ContactListData contactListData;
    private ContactAPI api;

    public ContactRepository() {
        //AppDB db = AppDB.getInstance();
        //dao = db.contactDao();
        contactListData = new ContactListData();
        //api = new ContactAPI(contactListData, dao);
    }

    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData() {
            super();
            List<Contact> con = new ArrayList<>();
            con.add(new Contact("shoval", "shovi", "hi", "5/5/5", "localhost:555"));
            setValue(con);
        }

        @Override
        protected void onActive() {
            super.onActive();

//            new Thread(() ->
//            {
//                userListData.postValue(dao.get());
//            }).start();

        }

    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

//    public void add
//            (final Post post) {
//        20 api.add(post);
//        21
//    }
//22
//        23 public void delete
//            (final Post post) {
//        24 api.delete(post);
//        25
//    }
//26
//        27 public void reload() {
//        28 api.get();
//        29
//    }

}