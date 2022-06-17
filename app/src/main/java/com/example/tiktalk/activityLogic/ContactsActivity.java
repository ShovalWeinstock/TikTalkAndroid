package com.example.tiktalk.activityLogic;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tiktalk.LoggedInUser;
import com.example.tiktalk.R;
import com.example.tiktalk.adapters.ContactListAdapter;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.viewModels.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContactsActivity extends AppCompatActivity implements ContactListAdapter.onContactListener{
    private List<Contact> contacts;
    private ContactListAdapter adapter;
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        adapter = new ContactListAdapter(this, this);

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        RecyclerView lvContacts = findViewById(R.id.lstContacts);
        lvContacts.setAdapter(adapter);
        lvContacts.setLayoutManager(new LinearLayoutManager(this));

        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reload();
        });

        // get contacts list and view it, using the adapter
        viewModel.get().observe(this, contacts -> {
            this.contacts = contacts;
            adapter.setContacts(contacts);
//            refreshLayout.setRefreshing(false); // todo add? its in the lecture notes, and not on the video
        });


        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        //add contact
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

//        //delete contact
//        lvContacts.setOnItemLongClickListener((adapterView, view, i, l) -> {
//            Contact contact = contacts.remove(i);
//            contactDao.delete(contact);
//            adapter.notifyDataSetChanged();
//            return true;
//        });
//
        //edit contact
//        lvContacts.setOnItemClickListener((adapterView, view, i, l) -> {
//            Intent intent = new Intent(this, UpdateContactActivity.class);
////            intent.putExtra("id", contacts.get(i).getId());
//            intent.putExtra("id", viewModel.get().getValue().get(i).getId());
//            startActivity(intent);
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //recreate the contact list
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
//            refreshLayout.setRefreshing(false); // todo add? its in the lecture notes, and not on the video
        });
    }
    @Override
    public void onContactClick(int position) {
        if(position > RecyclerView.NO_POSITION) {
            LoggedInUser.currentContact = contacts.get(position); //the clicked contact
            Intent i = new Intent(this, ChatActivity.class);
            startActivity(i);
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        contacts.clear();
//        //recreate the contact list
//        contacts.addAll(contactDao.index());
//        adapter.notifyDataSetChanged();
//    }
}