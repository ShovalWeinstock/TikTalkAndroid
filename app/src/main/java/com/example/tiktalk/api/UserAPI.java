package com.example.tiktalk.api;

import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.MyApplication;
import com.example.tiktalk.R;
import com.example.tiktalk.User;
import com.example.tiktalk.UserDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserAPI {
    private MutableLiveData<List<User>> usersListData;
    private UserDao dao;
    private boolean userExsits;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

        public UserAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.userExsits = false;
    }

//    public UserAPI(MutableLiveData<List<User>> usersListData, UserDao dao) {
//        this.usersListData = usersListData;
//        this.dao = dao;
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        webServiceAPI = retrofit.create(WebServiceAPI.class);
//    }


    public void getUser(String id) {
        Call<User> call = webServiceAPI.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //user = response.body();
                User u = response.body(); //todo delete
                int i = 5;
                if(u != null){
                    userExsits = true;
                }
            }
//                new Thread(() -> {
//                    dao.clear();
//                    dao.insertList(response.body());
//                    postListData.postValue(dao.get());
//                }).start();
//            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public boolean isUserExsits() {return userExsits;}

    public void addUser(User user) {
        Call<Void> call = webServiceAPI.createUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int i = 5;
            }
//                new Thread(() -> {
//                    dao.clear();
//                    dao.insertList(response.body());
//                    postListData.postValue(dao.get());
//                }).start();
//            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}