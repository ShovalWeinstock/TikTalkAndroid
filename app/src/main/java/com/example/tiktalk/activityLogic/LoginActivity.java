package com.example.tiktalk.activityLogic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.R;


public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button login_btn;
    TextView login_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // username and password input bars
        et_username = findViewById(R.id.loginUsername);
        et_password = findViewById(R.id.loginPassword);
        login_error = findViewById(R.id.login_error);

        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(v -> {
            // get username and password, and validate them
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            Boolean check = validate(username, password);

            // if the data is valid - login to user
            if(check) {
                login_error.setText("");
                LoggedInUser.setLoggedInUser(username, password);// todo get nickname instead of password
                Intent i = new Intent(this, ContactsActivity.class);
                startActivity(i);
            }
            // otherwise - show error
            else {
                login_error.setText(R.string.login_error_msg);
            }
        });

        // go to registration page
        TextView click_to_register = findViewById(R.id.click_to_register);
        click_to_register.setOnClickListener(v -> {
            Intent i = new Intent(this, RegistrationActivity.class);
            startActivity(i);
        });
    }

    // validate username and password
    private Boolean validate(String username, String password) {
        return true;
    }
}