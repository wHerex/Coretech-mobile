package com.example.coretech_mobile.calendar;


import com.example.coretech_mobile.model.Event;

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
