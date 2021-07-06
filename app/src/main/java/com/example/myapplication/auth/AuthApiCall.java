package com.example.myapplication.auth;

import com.example.myapplication.model.MyCallback;
import com.example.myapplication.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthApiCall {

    @POST("users/login")
    Call<MyCallback> login(@Body User user);

    @POST("users/register")
    Call<MyCallback> register(@Body User user);

    @GET("users/{login}")
    User getPrivilegeByLogin(@Path("login") String login);
}
