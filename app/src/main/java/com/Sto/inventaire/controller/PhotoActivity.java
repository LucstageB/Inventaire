package com.Sto.inventaire.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Sto.inventaire.R;

public class PhotoActivity extends AppCompatActivity {

    Button mPhotoButton1;
    Button mPhotoButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        mPhotoButton1 = findViewById(R.id.photo_activity_button_panier);
        mPhotoButton2 = findViewById(R.id.photo_activity_button_Quit);


        mPhotoButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PhotoActivity.this, ShopActivity.class));
            }

        });
        mPhotoButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            }
        });
    }
}