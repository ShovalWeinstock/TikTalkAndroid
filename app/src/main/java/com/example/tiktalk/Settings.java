package com.example.tiktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.tiktalk.activityLogic.RegistrationActivity;

public class Settings extends AppCompatActivity {

    EditText et_server;
    Button save_settings_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // username and password input bars
        et_server = findViewById(R.id.settingsServer);

        save_settings_btn = findViewById(R.id.save_settings_btn);
        save_settings_btn.setOnClickListener(v -> {
            // get username and password, and validate them
            String server = et_server.getText().toString();
            Boolean check = validate(server); //todo validate?

            // if the data is valid - login to user
            if(check) {
                // todo save the changes
                Intent i = new Intent(this, RegistrationActivity.class); //todo change "Registration" to contacts list
                startActivity(i);
            }
        });
    }

    // validate username and password
    private Boolean validate(String server) {
        return false; // todo compare with db

    }
}