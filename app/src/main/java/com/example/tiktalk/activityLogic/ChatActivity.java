package com.example.tiktalk.activityLogic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tiktalk.adapters.ChatAdapter;
import com.example.tiktalk.models.Message;
import com.example.tiktalk.viewModels.MessageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    //private List<Message> messages;
    private ChatAdapter adapter;
    private MessageViewModel viewModel;

    // todo change
    FloatingActionButton back_btn;
    TextView contactNickname;
    EditText typingArea;
    FloatingActionButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        adapter = new ChatAdapter(this);

        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        back_btn = findViewById(R.id.back_btn);
        // back to contacts list
        back_btn.setOnClickListener(view -> {
            Intent i = new Intent(this, ContactsActivity.class);
            startActivity(i);
        });

        contactNickname = findViewById(R.id.contactNickname);
        contactNickname.setText(LoggedInUser.getCurrentContact().getName());

        RecyclerView lvMessages = findViewById(R.id.lstMessages);
        lvMessages.setAdapter(adapter);
        lvMessages.setLayoutManager(new LinearLayoutManager(this));

        // todo add chat if not already did
        // get contacts list and view it, using the adapter
        viewModel.get().observe(this, messages -> {
            adapter.setChat(messages);
//            refreshLayout.setRefreshing(false); // todo add? its in the lecture notes, and not on the video
        });

        typingArea = findViewById(R.id.typingArea);

        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            // get message
            String content = typingArea.getText().toString();
            Message newMsg = new Message("", true, content);
            newMsg.setChatWith(LoggedInUser.getCurrentContact().getId());
            //add message to me
            viewModel.add(newMsg);
            // todo add message to other
        });






//    RecyclerView chat = findViewById(R.id.lstMessages);
//    final ChatAdapter adapter = new ChatAdapter(this);
//        lvContacts.setAdapter(adapter);
//        lvContacts.setLayoutManager(new LinearLayoutManager(this));

//    // get contacts list and view it, using the adapter
//        viewModel.get().observe(this, contacts -> {
//        adapter.setContacts(contacts);
//        //refreshLayout.setRefreshing(false); // todo add? its in the lecture notes, and not on the video
//    });


    }




}