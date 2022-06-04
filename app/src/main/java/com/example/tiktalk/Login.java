package com.example.tiktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText et_username, et_password;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.loginUsername);
        et_password = findViewById(R.id.loginPassword);

        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            Boolean check = validate(username, password);
            if(check) {
                Intent i = new Intent(this, Registration.class); //todo change "Registration" to contacts list
                startActivity(i);
            }
        });

        TextView click_to_register = findViewById(R.id.click_to_register);
        click_to_register.setOnClickListener(v -> {
            Intent i = new Intent(this, Registration.class);
            startActivity(i);
        });


    }

    private Boolean validate(String username, String password) {
        return true; // todo compare with db
    }
}