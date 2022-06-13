package com.example.tiktalk.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.repositories.ContactRepository;

import java.util.List;

public class ContactViewModel extends ViewModel {

    private ContactRepository repository;
    private LiveData<List<Contact>> contacts;
    private static volatile ContactViewModel myViewModel;

    public ContactViewModel() {
        repository = new ContactRepository();
        contacts = repository.getAll();
    }
    public static ContactViewModel getViewModel(){
        if (myViewModel == null){
            myViewModel = new ContactViewModel();
        }
        return myViewModel;
    }
    public LiveData<List<Contact>> get() {
        return contacts;
    }

    public void add(Contact contact) {
        repository.add(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public void reload() {
        repository.reload();
    }
}






