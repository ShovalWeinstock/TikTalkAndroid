package com.example.tiktalk.viewModels;

import androidx.lifecycle.LiveData;

import com.example.tiktalk.User;
import com.example.tiktalk.repositories.UsersRepository;

import java.util.List;

public class UsersViewModel {
    private UsersRepository repository;

    private LiveData<List<User>> users;

    public UsersViewModel() {
        repository = new UsersRepository();
        users = repository.getAll();
    }










    public LiveData<List<User>> get() {
        return users;
    }

    public void add(User user) {
        repository.add(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void reload() {
        repository.reload();
    }
}






