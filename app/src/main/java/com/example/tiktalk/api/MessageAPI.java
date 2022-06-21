package com.example.tiktalk.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.MessageDao;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.R;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {

    // contactsListData is the contacts local database
    private MutableLiveData<List<Message>> messagesListData;
    // communicate with local DB
    private MessageDao dao;
    // communicate with the DB (not local)
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public MessageAPI(MutableLiveData<List<Message>> messagesListData, MessageDao dao) {
        this.messagesListData = messagesListData;
        this.dao = dao;
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

        public void getChat() {
        Call<List<Message>> call = webServiceAPI.getChat(LoggedInUser.getCurrentContact().getId(), LoggedInUser.getUsername());
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> chat = response.body();
                String contact = LoggedInUser.getCurrentContact().getId();
                int size = chat.size();
                for (int i = 0; i < size; i++) {
                    chat.get(i).setChatWith(contact);
                }
                new Thread(() -> {
                    dao.clearByContact(contact);
                    dao.insertList(chat);
                    messagesListData.postValue(dao.getChatWithContact(LoggedInUser.getCurrentContact().getId()));
                }).start();
            }



            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {}
        });
    }


    // todo add
    public void addMessageToServer(Message newMsg) {
        Call<Void> call = webServiceAPI.addMsg(newMsg.getChatWith(),
                                               LoggedInUser.getUsername(), newMsg.getContent());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                  Log.i("h","h");
//                if(!response.isSuccessful()) {
//
//                };
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            Log.i("h","h");
            }
        });
    }













    // get all the contacts of the logged-in user (update the given MutableLiveData object)
//    public void get() {
//        Call<List<Contact>> call = webServiceAPI.getContacts(LoggedInUser.username);
//        call.enqueue(new Callback<List<Contact>>() {
//            @Override
//            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
//                //List<Contact> Contacts =  response.body();//todo delete
////                contacts.setValue(response.body());
//                new Thread(() -> {
//                    dao.clear();
//                    dao.insertList(response.body());
//                    contactsListData.postValue(dao.index());
//                }).start();
//            }
//
//            @Override
//            public void onFailure(Call<List<Contact>> call, Throwable t) {}
//        });
//    }

//    public void add(Contact contact) { // todo implement
//        Call<Void> call = webServiceAPI.addContact(
//                LoggedInUser.username,
//                contact
//        );
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
////                List<Contact> Contacts =  response.body();//todo delete
////                contacts.setValue(response.body());
//                response.isSuccessful();// todo:do something
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {}
//        });
//    }
//    public void update(Contact contact) { // todo implement
//    }
//
//    public void delete(Contact contact) { // todo implement
//    }

}
