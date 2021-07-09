package com.example.coretech_mobile.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.calendar.CalendarActivity;
import com.example.coretech_mobile.dashboard.DashboardActivity;
import com.example.coretech_mobile.model.MyCallback;
import com.example.coretech_mobile.model.Status;
import com.example.coretech_mobile.model.User;
import com.example.coretech_mobile.product.ProductActivity;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInTabFragment extends Fragment {

    AuthApiCall authApiCall;

    EditText loginEditText, passwordEditText;
    Button signInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signin_tab_fragment, container, false);

        signInButton = root.findViewById(R.id.signIn_signIn_button);
        loginEditText = root.findViewById(R.id.signIn_login_editText);
        passwordEditText = root.findViewById(R.id.signIn_password_editText);
        signInButton.setOnClickListener(c -> loginInto());

        authApiCall = getAuthApiCall();

        return root;
    }

    private void loginInto() {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(login.equals("")) {
            Toast.makeText(getView().getContext(), "Missing login!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals("")) {
            Toast.makeText(getView().getContext(), "Missing password!", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(login, password);
        authApiCall.login(user).enqueue(new Callback<MyCallback>() {
            @Override
            public void onResponse(Call<MyCallback> call, Response<MyCallback> response) {
                if (response.body() != null && response.body().getStatus() == Status.SUCCESS) {
                    String privilege = response.body().getUser().getPrivilege();
                    switch (privilege) {
                        case "USER":
                            startIntent(DashboardActivity.class, login);
                            break;
                        case "ADMIN":
                            startIntent(ProductActivity.class, login);
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

    private void startIntent(Class intentClass, String login) {
        Intent intent = new Intent(getView().getContext(), intentClass);
        intent.putExtra("login", login);
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