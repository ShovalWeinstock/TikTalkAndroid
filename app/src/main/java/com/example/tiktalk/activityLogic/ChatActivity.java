package com.example.tiktalk.activityLogic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiktalk.models.Contact;
import com.example.tiktalk.viewModels.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ChatActivity extends AppCompatActivity {

     // todo change
    Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        back_btn = findViewById(R.id.back_btn);
        // back to contacts list
        back_btn.setOnClickListener(view -> {
            Intent i = new Intent(this, ContactsActivity.class);
            startActivity(i);
        });

//    viewModel = new ViewModelProvider(this).get(ContactViewModel .class);
//
//    RecyclerView chat = findViewById(R.id.lstContacts);
//    final ContactListAdapter adapter = new ContactListAdapter(this);
//        lvContacts.setAdapter(adapter);
//        lvContacts.setLayoutManager(new LinearLayoutManager(this));
//
//    // get contacts list and view it, using the adapter
//        viewModel.get().observe(this, contacts -> {
//        adapter.setContacts(contacts);
//        //refreshLayout.setRefreshing(false); // todo add? its in the lecture notes, and not on the video
//    });


    }




}