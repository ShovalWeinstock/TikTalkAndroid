package com.example.tiktalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.tiktalk.adapters.ContactListAdapter;
import com.example.tiktalk.viewModels.ContactViewModel;

public class ChatActivity extends AppCompatActivity {
    private ContactViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

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