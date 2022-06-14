package com.example.tiktalk.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.repositories.ContactRepository;

import java.util.List;

public class ContactViewModel extends ViewModel {

    private ContactRepository repository;
    private LiveData<List<Contact>> contacts;
//    private LiveData<List<Message>> currentChat;

    public ContactViewModel() {
        repository = new ContactRepository();
        contacts = repository.getAll();
        //currentChat = repository.getChat();
    }

    public LiveData<List<Contact>> get() {
        return contacts;
    }

//    public LiveData<List<Message>> getChat() {
//        return currentChat;
//    }

    //todo getChat


    public void add(Contact contact) {
        repository.add(contact);
    }

    public void reload() {
        repository.reload();
    }
}






