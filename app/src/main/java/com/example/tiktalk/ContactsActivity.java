package com.example.tiktalk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        // reference to Dao
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactDB")
            .allowMainThreadQueries()
            .build();
        contactDao = db.contactDao();

        // adapter from list into linear item view
        contacts = new ArrayList<>();
        ListView lvContacts = findViewById(R.id.lvContact);
        adapter = new ArrayAdapter<>(this,
                                            android.R.layout.simple_list_item_1,
                                            contacts);
        lvContacts.setAdapter(adapter);

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        //add contact
        btnAdd.setOnClickListener(view->{
            Intent i = new Intent(this, AddContact.class);
            startActivity(i);
        });

        //delete contact
         lvContacts.setOnItemLongClickListener((adapterView, view, i, l) -> {
             Contact contact = contacts.remove(i);
             contactDao.delete(contact);
             adapter.notifyDataSetChanged();
             return true;
             });

         //edit contact
        lvContacts.setOnItemClickListener((adapterView, view, i, l) -> {
             Intent intent = new Intent(this, UpdateContactActivity.class);
             intent.putExtra("id", contacts.get(i).getId());
             startActivity(intent);
             });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        //recreate the contact list
        contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();

    }
}