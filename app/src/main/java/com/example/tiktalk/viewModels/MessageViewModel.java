package com.example.tiktalk.viewModels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.repositories.ContactRepository;
import com.example.tiktalk.repositories.MessageRepository;

import java.util.List;

public class MessageViewModel extends ViewModel{

    private MessageRepository repository;
    private LiveData<List<Message>> messages;

    public MessageViewModel() {
        repository = new MessageRepository();
        messages = repository.getAll();
    }

    public LiveData<List<Message>> get() {
        return messages;
    }

//    public Message getSpecific(String id) {
//        return messages.getValue().get(messages.getValue().indexOf(id));
//    }
//
    public void add(Message message) {
        repository.add(message);
    }
//
//    public void update(Contact message) {
//        repository.update(message);
//    }
//
//    public void delete(Contact contact) {
//        repository.delete(message);
//    }
//
    public void reload() {
        repository.reload();
    }


}
