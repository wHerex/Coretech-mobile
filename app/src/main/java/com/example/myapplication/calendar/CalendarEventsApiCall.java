package com.example.myapplication.calendar;


import com.example.myapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CalendarEventsApiCall {

    @GET("events")
    Call<List<Event>> getAllEvents();

    @POST("events")
    Call<Void> saveEvent(@Body Event event);
}
