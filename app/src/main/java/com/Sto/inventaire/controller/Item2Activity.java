package com.Sto.inventaire.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.Sto.inventaire.R;

public class Item2Activity extends AppCompatActivity {

    Button mButton;
    Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);

        mButton = findViewById(R.id.Item2_Activity_Continue);
        mButton2 = findViewById(R.id.Item2_Activity_Quit);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(Item2Activity.this, PhotoActivity.class));
            }
        });
    }
}

