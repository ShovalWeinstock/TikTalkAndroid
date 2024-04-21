package com.example.tiktalk.activityLogic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.R;
import com.example.tiktalk.api.ToOther;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.viewModels.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AddContactActivity extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    private ContactViewModel viewModel;
    EditText et_username, et_nickname, et_server;
    Button add_contact_btn;
    FloatingActionButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        //get the contact viewModel
        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // username and password input bars
        et_username = findViewById(R.id.addContactUsername);
        et_nickname = findViewById(R.id.addContactNickname);
        et_server = findViewById(R.id.addContactServer);

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(view -> {
            finish();
        });

        add_contact_btn = findViewById(R.id.add_contact_btn);
        add_contact_btn.setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String nickname = et_nickname.getText().toString();
            String server = et_server.getText().toString();
            Boolean check = validate(username, server);

            // if the data is valid - add contact
            if(check) {
                Contact contact = new Contact(username, nickname,"","", server);
                viewModel.add(contact);
                ToOther toOther = new ToOther(server);
                toOther.addContactToOther(username);
                finish();
            }
        });
    }

    private Boolean validate(String username, String server) {
        return true;

    }
}
