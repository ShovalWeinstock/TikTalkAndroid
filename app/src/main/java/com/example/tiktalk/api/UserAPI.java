package com.example.tiktalk.api;

import android.content.Intent;
import android.widget.TextView;
import android.content.Intent;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.R;
import com.example.tiktalk.activityLogic.ContactsActivity;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.User;
import com.example.tiktalk.UserDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void login(String id, String password, TextView login_error) {
        Call<User> call = webServiceAPI.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && user.getPassword().equals(password)) {
                    login_error.setText("");
                    LoggedInUser.setLoggedInUser(id, user.getName());
                    Intent i = new Intent(MyApplication.context, ContactsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.context.startActivity(i);
                } else {
                    login_error.setText(R.string.login_error_msg);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }


    public void register(User user, TextView usernameExists) {
        Call<User> call = webServiceAPI.getUser(user.getId());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                if (u != null) {
                    usernameExists.setError("Username already exists");

                }
                else {
                    addUserToServer(user);
                    LoggedInUser.setLoggedInUser(user.getId(), user.getName());
                    Intent i = new Intent(MyApplication.context, ContactsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void addUserToServer(User user) {
        Call<Void> call = webServiceAPI.addUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                if(!response.isSuccessful()) {
//
//                };
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }



}