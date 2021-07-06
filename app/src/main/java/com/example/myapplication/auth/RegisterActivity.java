package com.example.myapplication.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coretech_mobile.R;
import com.example.myapplication.model.MyCallback;
import com.example.myapplication.model.User;
import com.example.myapplication.product.ProductApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText register_login_text;
    EditText register_password_text;
    EditText register_privilege_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        Button login_back_b = findViewById(R.id.register_back_b);
        Button register_b = findViewById(R.id.register_b);

        register_login_text = findViewById(R.id.register_login_text);
        register_password_text = findViewById(R.id.register_password_text);
        register_privilege_text = findViewById(R.id.register_privilege_text);

        AuthApiCall authApiCall = getAuthApiCall();


        login_back_b.setOnClickListener(v -> backToMainActivity());
        register_b.setOnClickListener(v -> register(authApiCall));

    }

    private void register(AuthApiCall authApiCall) {
        String login = register_login_text.getText().toString();
        String password = register_password_text.getText().toString();
        String privilege = register_privilege_text.getText().toString();
        User newUser = new User(login, password, privilege);
        authApiCall.register(newUser).enqueue(new Callback<MyCallback>() {
            @Override
            public void onResponse(Call<MyCallback> call, Response<MyCallback> response) {
                Toast.makeText(getApplicationContext(), response.body().getStatus().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<MyCallback> call, Throwable t) {

            }
        });
    }

    public void backToMainActivity() {
        finish();
    }

    private AuthApiCall getAuthApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coretech-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(AuthApiCall.class);

    }


}
