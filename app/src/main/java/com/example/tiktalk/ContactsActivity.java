package com.example.tiktalk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.tiktalk.adapters.ContactListAdapter;
import com.example.tiktalk.viewModels.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class ContactsActivity extends AppCompatActivity implements ContactListAdapter.onContactListener{
    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ArrayAdapter<Contact> adapter;
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        RecyclerView lvContacts = findViewById(R.id.lstContacts);
        final ContactListAdapter adapter = new ContactListAdapter(this, this);
        lvContacts.setAdapter(adapter);
        lvContacts.setLayoutManager(new LinearLayoutManager(this));

        // hard coded
        //List<Contact> lst = new ArrayList<>();
        //lst.add(new Contact("String id", "String name", "String last", "String lastDate", "String server"));
        //lst.add(new Contact("String id2", "String name2", "String last", "String lastDate", "String server"));
        //adapter.setContacts(lst);

        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reload();
        });

        // get contacts list and view it, using the adapter
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
            //refreshLayout.setRefreshing(false); // todo add? its in the lecture notes, and not on the video
        });


        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        //add contact
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContact.class);
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
//            intent.putExtra("id", contacts.get(i).getId());
//            startActivity(intent);
//        });

    }

    @Override
    public void onContactClick(int position) {
        //contacts.get(position); //the clicked contact
        Intent i = new Intent(this, ChatActivity.class);
        startActivity(i);
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