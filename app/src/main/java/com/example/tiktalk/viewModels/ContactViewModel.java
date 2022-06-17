package com.example.tiktalk.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.repositories.ContactRepository;

import java.util.List;

public class ContactViewModel extends ViewModel {

    private ContactRepository repository;
    private LiveData<List<Contact>> contacts;

    public ContactViewModel() {
        repository = new ContactRepository();
        contacts = repository.getAll();
    }

    public LiveData<List<Contact>> get() {
        return contacts;
    }

    public Contact getSpecific(String id) {
        return contacts.getValue().get(contacts.getValue().indexOf(id));
    }

    public void add(Contact contact) {
        repository.add(contact);
    }

    public void update(Contact contact) {
        repository.update(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public void reload() {
        repository.reload();
    }
}






