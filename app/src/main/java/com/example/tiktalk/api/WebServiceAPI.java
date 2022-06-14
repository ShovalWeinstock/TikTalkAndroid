package com.example.tiktalk.api;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {
    @GET("user")
    Call<List<User>> getUsers();

    @GET("user/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("user")
    Call<Void> createUser(@Body User user);

    @DELETE("user/{id}")
    Call<Void> deleteUser(@Path("id") String id);

    @GET("contacts")
    Call<List<Contact>> getContacts(@Query("user") String id);

    @GET("contacts/{contact}/messages?user={id}")
    Call<List<Message>> getChat(@Query("user") String id, @Path("contact") String contact);

    @POST("contacts/{contact}/messages?user={id}")
    Call<Void> addMsg(@Query("user") String id, @Path("contact") String contact, @Body String content);

    @POST("contacts")
    Call<Void> addContact(@Query("user") String userId,
                          @Body Contact contact);

    // transfer @POST()
    // invitations

}
