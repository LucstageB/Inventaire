package com.Sto.inventaire.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.Sto.inventaire.R;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle saveInstancesState) {
        super.onCreate(saveInstancesState);
        setContentView(R.layout.activity_main);


        mEditText = findViewById(R.id.edit_text);
        mButton = findViewById(R.id.button);

        mButton.setEnabled(false);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mButton.setEnabled(!editable.toString().isEmpty());
            }

        });


        mButton.setOnClickListener(view -> {
            Intent photoActivityIntent = new Intent(MainActivity.this, PhotoActivity.class);
            startActivity(photoActivityIntent);
        });

    }
}