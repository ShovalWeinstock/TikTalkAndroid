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
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

        public UserAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
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

    public void get(MutableLiveData<List<User>> users) {
        Call<List<User>> call = webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users.setValue(response.body());
                //List<User> users =  response.body();//todo delete

//                new Thread(() ->
//                {
//                    dao.clear();
//                    dao.insertList(response.body());
//                    postListData.postValue(dao.get());
//                }).start();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }

    public void getUser(String id, MutableLiveData<User> user) {
        Call<User> call = webServiceAPI.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user.setValue(response.body());
                //User user = response.body(); //todo delete
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
}