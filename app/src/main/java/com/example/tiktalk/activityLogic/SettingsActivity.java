package com.example.tiktalk.activityLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tiktalk.R;
import com.example.tiktalk.activityLogic.RegistrationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {

    EditText et_server;
    Button save_settings_btn;
    TextView settings_error;
    FloatingActionButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        et_server = findViewById(R.id.settingsServer);
        settings_error = findViewById(R.id.settings_error);

        // save button
        save_settings_btn = findViewById(R.id.save_settings_btn);
        save_settings_btn.setOnClickListener(v -> {
            String server = et_server.getText().toString();
            settings_error.setText("Cannot change Server");
        });

        // back to contacts list button
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            finish();
        });
    }
}