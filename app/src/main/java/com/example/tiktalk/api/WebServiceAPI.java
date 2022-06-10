package com.example.tiktalk.api;

import com.example.tiktalk.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    @GET("user")
     Call<List<User>> getUsers();

    @POST("user")
    Call<Void> createPost(@Body User user);

    @DELETE("user/{id}")
    Call<Void> deletePost(@Path("id") String id);
 }
