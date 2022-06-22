package com.example.tiktalk.api;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.models.Invitation;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.models.Transfer;
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
    Call<Void> addUser(@Body User user);

    @DELETE("user/{id}")
    Call<Void> deleteUser(@Path("id") String id);

    @GET("contacts")
    Call<List<Contact>> getContacts(@Query("user") String id);

    @GET("contacts/{contactId}/messages")
    Call<List<Message>> getChat(@Path("contactId") String contact, @Query("user") String id);

    @POST("contacts/{contact}/messages")
    Call<Void> addMsg(@Path(value = "contact", encoded = true) String contact, @Query("user") String id, @Body String content);

    @POST("contacts")
    Call<Void> addContact(@Query("user") String userId,
                          @Body Contact contact);

    @POST("transfer")
    Call<Void> transfer(@Body Transfer transfer);


    @POST("invitations")
    Call<Void> invitation(@Body Invitation invitation);

    // invitations

}
