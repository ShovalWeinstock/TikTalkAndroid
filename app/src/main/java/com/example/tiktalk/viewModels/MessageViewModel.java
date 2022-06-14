package com.example.tiktalk.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.repositories.ContactRepository;
import com.example.tiktalk.repositories.MessageRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {

    private MessageRepository repository;
    private LiveData<List<Message>> currentChat;

    public MessageViewModel() {
        repository = new MessageRepository();
        currentChat = repository.getChat();
    }

    public LiveData<List<Message>> get() {
        return currentChat;
    }

    public LiveData<List<Message>> getChat() {
        return currentChat;
    }
//    public List<Message> getChat() {
//        return contacts.getValue().get(contacts.getValue()
//                        .indexOf(LoggedInUser.currentContact.getId()))
//                .getChatWithContact();
//    }

    //todo getChat


//    public void add(Message message) {
//        repository.add(message);
//    }

    public void reload() {
        repository.reload();
    }
}






