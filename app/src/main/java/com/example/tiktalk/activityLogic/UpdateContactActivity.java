package com.example.tiktalk.activityLogic;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.tiktalk.AppDB;
import com.example.tiktalk.models.Contact;
import com.example.tiktalk.ContactDao;
import com.example.tiktalk.R;

public class UpdateContactActivity extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    EditText et_nickname;
    Button updateContactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        //reference to Dao
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        contactDao = db.contactDao();

        // nickname bar with the current nickname
        et_nickname = findViewById(R.id.et_updateContactNickname);
        String id = getIntent().getExtras().getString("id");
        Contact contact = contactDao.get(id);
        et_nickname.setText(contact.getName());

        //save new name into the db
        updateContactBtn = findViewById(R.id.update_contact_btn);
        updateContactBtn.setOnClickListener(v -> {
            // get username and password, and validate them
                contact.setName(et_nickname.getText().toString());
                contactDao.update(contact);
            finish();
        });



    }
}