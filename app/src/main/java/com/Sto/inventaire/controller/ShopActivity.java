package com.Sto.inventaire.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.Sto.inventaire.R;

public class ShopActivity extends AppCompatActivity {

    Button mButton;
    Button mButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mButton = findViewById(R.id.return_page);
        mButton2 = findViewById(R.id.finish);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, PhotoActivity.class));
            }
        });
    }



}

