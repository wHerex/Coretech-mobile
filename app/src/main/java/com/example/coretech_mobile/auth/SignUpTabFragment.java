package com.example.coretech_mobile.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.model.MyCallback;
import com.example.coretech_mobile.model.User;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpTabFragment extends Fragment {

    EditText register_login_text;
    EditText register_password_text;
    EditText register_confirmPassword_text;
    private String privilege = "USER";  // Default value

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        Button register_b = root.findViewById(R.id.signUp_signUp_button);
        RadioButton userPrivilegeRadioButton = root.findViewById(R.id.signUp_user_radioButton);
        RadioButton adminPrivilegeRadioButton = root.findViewById(R.id.signUp_admin_radioButton);

        userPrivilegeRadioButton.setOnClickListener(v -> onPrivilegeRadioButtonClicked(v));
        adminPrivilegeRadioButton.setOnClickListener(v -> onPrivilegeRadioButtonClicked(v));

        register_login_text = root.findViewById(R.id.signUp_login_editText);
        register_password_text = root.findViewById(R.id.signUp_password_editText);
        register_confirmPassword_text = root.findViewById(R.id.signUp_confirmPassword_editText);

        AuthApiCall authApiCall = getAuthApiCall();

        register_b.setOnClickListener(v -> register(authApiCall));

        return root;
    }

    private void register(AuthApiCall authApiCall) {
        String login = register_login_text.getText().toString();
        String password = register_password_text.getText().toString();

        if(login.equals("")) {
            Toast.makeText(getView().getContext(), "Missing login!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals("")) {
            Toast.makeText(getView().getContext(), "Missing password!", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals(register_confirmPassword_text.getText().toString())) {
            User newUser = new User(login, password, privilege);
            authApiCall.register(newUser).enqueue(new Callback<MyCallback>() {
                @Override
                public void onResponse(Call<MyCallback> call, Response<MyCallback> response) {
                    Toast.makeText(getView().getContext(), response.body().getStatus().toString(), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<MyCallback> call, Throwable t) {

                }
            });
        }
        else {
            Toast.makeText(getView().getContext(), "Passwords are not the same!", Toast.LENGTH_SHORT).show();
        }
    }

    private AuthApiCall getAuthApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coretech-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(AuthApiCall.class);

    }

    public void onPrivilegeRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.signUp_user_radioButton:
                if (checked)
                    privilege = "USER";
                break;
            case R.id.signUp_admin_radioButton:
                if (checked)
                    privilege = "ADMIN";
                    break;
        }
    }

}
