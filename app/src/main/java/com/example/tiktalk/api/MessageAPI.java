package com.example.tiktalk.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.MessageDao;
import com.example.tiktalk.MyApplication;
import com.example.tiktalk.R;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.models.MessageContent;
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
        // connect to db
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    // get the chat with the current contact
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
            public void onFailure(Call<List<Message>> call, Throwable t) {
            }
        });
    }

    // add newMsg to the server
    public void addMessageToServer(Message newMsg) {
        MessageContent content = new MessageContent(newMsg.getContent());
        Call<Void> call = webServiceAPI.addMsg(newMsg.getChatWith(),
                LoggedInUser.getUsername(), content);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getChat();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
