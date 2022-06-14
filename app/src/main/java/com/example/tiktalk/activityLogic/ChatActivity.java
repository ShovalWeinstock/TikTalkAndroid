package com.example.tiktalk.activityLogic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktalk.R;
import com.example.tiktalk.adapters.ChatAdapter;
import com.example.tiktalk.viewModels.ContactViewModel;
import com.example.tiktalk.viewModels.MessageViewModel;

public class ChatActivity extends AppCompatActivity {
    private MessageViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        RecyclerView lstMessages = findViewById(R.id.lstMessages);

        final ChatAdapter adapter = new ChatAdapter(this);
        lstMessages.setAdapter(adapter);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));

        // get chat and view it, using the adapter
        viewModel.getChat().observe(this, messages -> {
        adapter.setChat(messages);
        //refreshLayout.setRefreshing(false); // todo add? its in the lecture notes, and not on the video
    });


    }

//








}