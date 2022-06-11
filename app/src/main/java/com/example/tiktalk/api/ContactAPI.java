package com.example.tiktalk.api;

import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.Contact;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.R;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    // contactsListData is the contacts local database
    private MutableLiveData<List<Contact>> contactsListData;
    //
    private ContactDao dao;
    // communicate with the DB
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ContactAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

//    public ContactAPI(MutableLiveData<List<Contact>> contactsListData, ContactDao dao) {
//        this.contactsListData = contactsListData;
//        this.dao = dao;
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        webServiceAPI = retrofit.create(WebServiceAPI.class);
//    }

    public void get(String id) {
        Call<List<Contact>> call = webServiceAPI.getContacts(id);
        call.enqueue(new Callback<List<Contact>>() {

            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                //users.setValue(response.body());
                //List<Contact> Contacts =  response.body();//todo delete
                contactsListData.postValue(response.body());
                int x = 0;

//                new Thread(() -> {
//                    dao.clear();
//                    dao.insertList(response.body());
//                    contactsListData.postValue(dao.index());
//                }).start();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {}
        });
    }

}