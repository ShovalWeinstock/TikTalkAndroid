package com.example.tiktalk.viewModels;

import androidx.lifecycle.LiveData;

import com.example.tiktalk.Contact;
import com.example.tiktalk.repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactViewModel {

    private ContactRepository repository;
    private LiveData<List<Contact>> contacts;

    public ContactViewModel() {
        repository = new ContactRepository();
        contacts = repository.getAll();



    }

    public LiveData<List<Contact>> get() {
        return contacts;
    }
//
//    public void add(User user) {
//        repository.add(user);
//    }
//
//    public void delete(User user) {
//        repository.delete(user);
//    }
//
//    public void reload() {
//        repository.reload();
//    }
}






