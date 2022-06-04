package com.example.tiktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.provider.MediaStore;
import android.widget.ImageView;


import android.os.Bundle;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ImageView imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(v -> {
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK,
                                                  MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGalleryIntent, 1000);

            @Override
            protected void onActivityResult(int requestCode, int resultCode, @android.annotation.Nullable Intent data) {

            }

        });

    }
}