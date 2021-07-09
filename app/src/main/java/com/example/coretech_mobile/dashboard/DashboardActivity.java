package com.example.coretech_mobile.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.calendar.CalendarActivity;
import com.example.coretech_mobile.calendar.CalendarEventsApiCall;
import com.example.coretech_mobile.model.Event;
import com.example.coretech_mobile.model.Product;
import com.example.coretech_mobile.product.MyRecyclerViewAdapter;
import com.example.coretech_mobile.product.ProductApiCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity implements DashbordRecyclerViewAdapter.OnEventListener {

    RecyclerView recyclerView;
    TextView textView;
    Button bookEventButton;
    String login;
    DashbordRecyclerViewAdapter.OnEventListener eventListener = this;
    CalendarEventsApiCall calendarEventsApiCall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_recycler_view);
        login = getIntent().getStringExtra("login");
        recyclerView = findViewById(R.id.dsh_recycler_view);
        bookEventButton = findViewById(R.id.add_event_button);
        bookEventButton.setOnClickListener(c -> {
                    startIntent(CalendarActivity.class, login);
                }
        );
        calendarEventsApiCall = getCalendarEventsApiCall();
        getEventsByLogin();
    }

    private void getEventsByLogin() {
        calendarEventsApiCall.getEventsByLogin(login).enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                DashbordRecyclerViewAdapter dashbordRecyclerViewAdapter = new DashbordRecyclerViewAdapter(getApplicationContext(), response.body(), eventListener);
                recyclerView.setAdapter(dashbordRecyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getEventsByLogin();
    }

    private void startIntent(Class intentClass, String login) {
        Intent intent = new Intent(this, intentClass);
        intent.putExtra("login", login);
        startActivity(intent);
    }

    private CalendarEventsApiCall getCalendarEventsApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coretech-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CalendarEventsApiCall.class);
    }

    @Override
    public void onProductClick(Event event) {

    }

    @Override
    public void onDeleteButtonClick(String id) {

    }
}
