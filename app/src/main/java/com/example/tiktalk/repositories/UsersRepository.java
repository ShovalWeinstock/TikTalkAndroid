package com.example.tiktalk.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.User;
import com.example.tiktalk.UserDao;
import com.example.tiktalk.api.UserAPI;

import java.util.LinkedList;
import java.util.List;

public class UsersRepository {
    //private UserDao dao;
    private UserListData userListData;
    private UserAPI api;

    public UsersRepository() {
        //LocalDatabase db = LocalDatabase.getInstance();
        //dao = db.userDao();
        //userListData = new UserListData();
        //api = new UserAPI(userListData, dao);
    }

    class UserListData extends MutableLiveData
            <List<User>> {
        public UserListData() {
            super();
            setValue(new LinkedList<User>());
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() ->
            {
                userListData.postValue(dao.get());
            }).start();

        }

    }

    public LiveData<List<User>> getAll() {
        return userListData;
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