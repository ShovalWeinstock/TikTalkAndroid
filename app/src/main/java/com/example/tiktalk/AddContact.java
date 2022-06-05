package com.example.tiktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddContact extends AppCompatActivity {

    EditText et_username, et_nickname, et_server;
    Button add_contact_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // username and password input bars
        et_username = findViewById(R.id.addContactUsername);
        et_nickname = findViewById(R.id.addContactNickname);
        et_server = findViewById(R.id.addContactServer);

        add_contact_btn = findViewById(R.id.add_contact_btn);
        add_contact_btn.setOnClickListener(v -> {
            // get username and password, and validate them
            String username = et_username.getText().toString();
            String nickname = et_nickname.getText().toString();
            String server = et_server.getText().toString();
            Boolean check = validate(username, server);

            // if the data is valid - login to user
            if(check) {
                // todo add the contact
                Intent i = new Intent(this, Registration.class); //todo change "Registration" to contacts list
                startActivity(i);
            }
        });
    }

    // validate username and password
    private Boolean validate(String username, String server) {
        return false; // todo compare with db

    }
}