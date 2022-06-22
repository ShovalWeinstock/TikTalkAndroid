package com.example.tiktalk.api;

import com.example.tiktalk.LoggedInUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.tiktalk.models.Invitation;
import com.example.tiktalk.models.Transfer;


public class ToOther {
    String server;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ToOther(String contactServer) {
        server = contactServer;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:" + server + "/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void sendToOther(String sendTo, String content) {
        Transfer transfer = new Transfer(LoggedInUser.getUsername(),
                sendTo, content);
        Call<Void> call = webServiceAPI.transfer(transfer);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });
    }


    public void addContactToOther(String addTo) {
        Invitation invitation = new Invitation(LoggedInUser.getUsername(),
                addTo, LoggedInUser.getServer());
        Call<Void> call = webServiceAPI.invitation(invitation);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });
    }
}
