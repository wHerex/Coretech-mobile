package com.example.coretech_mobile.calendar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalendarActivity extends AppCompatActivity implements CalendarDialog.CalendarDialogListener {

    CalendarView calendarView;
    List<Event> events;
    CalendarEventsApiCall calendarEventsApiCall;
    LocalDate choosedDate;
    String login;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendara_layout);
        login = getIntent().getStringExtra("login");
        initCalendarWithEvents();
        calendarView = findViewById(R.id.calendarView);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(c -> finish());
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                choosedDate = LocalDate.of(year, month + 1, dayOfMonth);
                List<LocalDate> dates = events.stream()
                        .map(Event::getStartDateTime)
                        .map(LocalDate::parse)
                        .collect(Collectors.toList());
                if (!dates.contains(choosedDate)) {
                    CalendarDialog calendarDialog = new CalendarDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("date", choosedDate.toString());
                    bundle.putString("login", login);
                    calendarDialog.setArguments(bundle);
                    calendarDialog.show(getSupportFragmentManager(), "CalendarDialog");
                } else {
                    CalerndarDialogBusy calerndarDialogBusy = new CalerndarDialogBusy();
                    calerndarDialogBusy.show(getSupportFragmentManager(), "CalendarDialogBusy");
                }
            }
        });
    }

    private void initCalendarWithEvents() {
        calendarEventsApiCall = getCalendarEventsApiCall();
        calendarEventsApiCall.getAllEvents().enqueue(new Callback<List<Event>>() {

            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                events = response.body();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
            }
        });
    }

    private CalendarEventsApiCall getCalendarEventsApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coretech-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CalendarEventsApiCall.class);
    }

    @Override
    public void saveEvent(Event event) {
        event.setStartDateTime(choosedDate.toString());
        calendarEventsApiCall.saveEvent(event).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                initCalendarWithEvents();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}