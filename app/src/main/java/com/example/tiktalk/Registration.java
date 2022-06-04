package com.example.tiktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import android.os.Bundle;

public class Registration extends AppCompatActivity {

    final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    EditText et_username, et_nickname, et_password, et_confirmation;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        et_username = findViewById(R.id.registrationUsername);
        et_nickname = findViewById(R.id.registrationNickname);
        et_password = findViewById(R.id.registrationPassword);
        et_confirmation = findViewById(R.id.registrationConfirmPassword);

        register_btn = findViewById(R.id.register_btn);

        register_btn.setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String nickname = et_nickname.getText().toString();
            String password = et_password.getText().toString();
            String confirmation = et_confirmation.getText().toString();

            Boolean valid = validate(username, password, confirmation);

            if(valid) {
                // todo register the user
                Intent i = new Intent(this, Login.class); //todo change "Login" to contacts list
                startActivity(i);
            }
        });

       // ImageView imageView = findViewById(R.id.imageView);
        //imageView.setOnClickListener(v -> {
          //  Intent openGalleryIntent = new Intent(Intent.ACTION_PICK,
            //                                      MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //startActivityForResult(openGalleryIntent, 1000);

            //@Override
            //protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {

        //});
    }

    private Boolean validate(String username, String password, String confirmation) {
        // validate username
        if(username.length() == 0) {
            et_username.requestFocus();
            et_username.setError("Username is required");
            return false;
        }
        else if(username.equals("exists")) { //todo check if username exists
            et_username.requestFocus();
            et_username.setError("Username already exists");
            return false;
        }
        // validate password
        if(password.length() == 0) {
            et_password.requestFocus();
            et_password.setError("Password is required");
            return false;
        }
        else if(!password.matches(PASSWORD_REGEX)) {
            et_password.requestFocus();
            et_password.setError("Invalid password");
        }
        // validate conformation
        if(confirmation.length() == 0) {
            et_confirmation.requestFocus();
            et_confirmation.setError("Password confirmation is required");
            return false;
        }
        else if(!confirmation.equals(password)) {
            et_confirmation.requestFocus();
            et_confirmation.setError("Passwords don't match");
            return false;
        }
        return true;
    }
}