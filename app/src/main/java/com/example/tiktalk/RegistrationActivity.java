package com.example.tiktalk;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.Bundle;

import com.example.tiktalk.api.UserAPI;

public class RegistrationActivity extends AppCompatActivity {

    final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    EditText et_username, et_nickname, et_password, et_confirmation;
    Button register_btn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // DB todo ?
//        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "UsersDB")
//                .allowMainThreadQueries()
//                .build();
//        UserDao userDao = db.userDao();

        // fields
        et_username = findViewById(R.id.registrationUsername);
        et_nickname = findViewById(R.id.registrationNickname);
        et_password = findViewById(R.id.registrationPassword);
        et_confirmation = findViewById(R.id.registrationConfirmPassword);
        imageView = findViewById(R.id.imageView);

        // registration button
        register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(v -> {
            // get user's input and validate it
            String username = et_username.getText().toString();
            String nickname = et_nickname.getText().toString();
            String password = et_password.getText().toString();
            String confirmation = et_confirmation.getText().toString();
            android.graphics.drawable.Drawable profilePic = imageView.getDrawable(); //todo image format

            // if the data is valid - register the user and login
            Boolean valid = validate(username, password, confirmation);
            if(valid) {
                // todo register the user
                //User user = new User(username, nickname, profilePic, password);
                User user = new User(username, nickname, password);
                //userDao.insert(user); // todo ?
                UserAPI api = new UserAPI();
                api.addUser(user);

                LoggedInUser.setLoggedInUser(username, nickname); //todo ?
                Intent i = new Intent(this, ContactsActivity.class);
                startActivity(i);
            }
        });

        // pick profile picture
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> imageView.setImageURI(uri)
        );
       imageView.setOnClickListener(v -> mGetContent.launch("image/*"));
    }



    // validate the registration data
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
        // validate password confirmation
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