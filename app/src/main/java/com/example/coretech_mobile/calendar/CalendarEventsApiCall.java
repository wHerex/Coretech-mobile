package com.example.coretech_mobile.calendar;


import com.example.coretech_mobile.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CalendarEventsApiCall {

    @GET("events")
    Call<List<Event>> getAllEvents();

    @GET("events/{login}")
    Call<List<Event>> getEventsByLogin(@Path("login") String login);

    @POST("events")
    Call<Void> saveEvent(@Body Event event);
}
