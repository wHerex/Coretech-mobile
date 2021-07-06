package com.example.coretech_mobile.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.calendar.CalendarActivity;
import com.example.coretech_mobile.model.MyCallback;
import com.example.coretech_mobile.model.Status;
import com.example.coretech_mobile.model.User;
import com.example.coretech_mobile.product.ProductActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    AuthApiCall authApiCall;

    EditText loginEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginTestButton);
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton.setOnClickListener(c -> loginInto());

        authApiCall = getAuthApiCall();
    }

    private void loginInto() {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        User user = new User(login, password);
        authApiCall.login(user).enqueue(new Callback<MyCallback>() {
            @Override
            public void onResponse(Call<MyCallback> call, Response<MyCallback> response) {
                if (response.body() != null && response.body().getStatus() == Status.SUCCESS) {
                    String privilege = response.body().getUser().getPrivilege();
                    switch (privilege) {
                        case "USER":
                            startIntent(CalendarActivity.class);
                            break;
                        case "ADMIN":
                            startIntent(ProductActivity.class);
                            break;
                        default:
                            System.out.println("DEFAULT");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<MyCallback> call, Throwable t) {

            }
        });

    }

    private void startIntent(Class intentClass) {
        Intent intent = new Intent(this, intentClass);
        startActivity(intent);
    }

    private AuthApiCall getAuthApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coretech-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(AuthApiCall.class);
    }
}