package com.example.tiktalk.viewModels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktalk.models.Message;
import com.example.tiktalk.repositories.MessageRepository;

import java.util.List;

public class MessageViewModel extends ViewModel{

    private MessageRepository repository;
    private LiveData<List<Message>> messages;

    public MessageViewModel() {
        repository = new MessageRepository();
        messages = repository.getCurrentChat();
    }

    public LiveData<List<Message>> get() {
        return messages;
    }

    public void add(Message message) {
        repository.add(message);
    }

    public void reload() {
        repository.reload();
    }


}
