package com.example.tiktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(v -> {
            //go to contacts list
        });

        TextView click_to_register = findViewById(R.id.click_to_register);

        click_to_register.setOnClickListener(v -> {
            Intent i = new Intent(this, Registration.class);
            startActivity(i);
        });


    }

}