package com.example.tiktalk;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactsActivity extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactDB")
            .allowMainThreadQueries()
            .build();
        contactDao = db.contactDao();
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view->{
            Intent i = new Intent(this, AddContact.class);
            startActivity(i);
        });
    }
}