package com.example.coretech_mobile.auth;

import com.example.coretech_mobile.model.MyCallback;
import com.example.coretech_mobile.model.User;

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

}
