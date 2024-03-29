package com.example.tiktalk.api;

import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.LoggedInUser;
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
    // communicate with local DB
    private ContactDao dao;
    // communicate with the DB (not local)
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ContactAPI(MutableLiveData<List<Contact>> contactsListData, ContactDao dao) {
        this.contactsListData = contactsListData;
        this.dao = dao;
        // connect to db
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    // get all the contacts of the logged-in user (update the given MutableLiveData object)
    public void get() {
        Call<List<Contact>> call = webServiceAPI.getContacts(LoggedInUser.getUsername());
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                 new Thread(() -> {
                    dao.clear();
                    dao.insertList(response.body());
                    contactsListData.postValue(dao.index());
                }).start();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {}
        });
    }

    // add the contact to the loggedIn user
    public void add(Contact contact) {
        Call<Void> call = webServiceAPI.addContact(
                LoggedInUser.getUsername(),
                contact
                );
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });
    }

    public void update(Contact contact) {
    }

    public void delete(Contact contact) {
    }
}
