package com.example.tiktalk.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.api.ContactAPI;
import com.example.tiktalk.models.Contact;

import java.util.List;

class ContactListData extends MutableLiveData<List<Contact>> {
    private ContactDao contactdao;
    // contacts local DB
    private ContactAPI api;
    private AppDB db;

    public ContactListData(ContactDao contactDao, ContactAPI contactAPI, AppDB appDB) {
        super();
        contactdao = contactDao;
        api = contactAPI;
        db = appDB;

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
        contactdao = db.contactDao();
        // update the mutable live data
        postValue(contactdao.index());
        new Thread(() -> {
            //sleep for emphasis the communication with the server
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // not local database
//            api.get();
        }).start();
    }
}
