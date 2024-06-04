package com.example.vazhikatti;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {


    @POST("student")
    Call<User> createUser(@Body User user);
    @POST("mendor")
    Call<User> createMendor(@Body User user);
    @POST("studentlogin")
    Call<User> login(@Body User user);

    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);

    @PUT("users/{userId}")
    Call<User> updateUser(@Path("userId") int userId, @Body User user);

    @DELETE("users/{userId}")
    Call<Void> deleteUser(@Path("userId") int userId);
}
