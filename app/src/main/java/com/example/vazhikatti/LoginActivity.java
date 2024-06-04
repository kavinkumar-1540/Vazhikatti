package com.example.vazhikatti;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {
    private TextInputLayout mobileLayout, passwordLayout;
    private TextInputEditText mobileEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobileLayout = findViewById(R.id.mobilenumber);
        passwordLayout = findViewById(R.id.password);
        mobileEditText = findViewById(R.id.mobileText);
        passwordEditText = findViewById(R.id.passwordText);


    }

    public void signup_user(View view) {
    }

    public void home_page(View view) {
        Intent intent = new Intent(LoginActivity.this, Home.class);
        startActivity(intent);
    }
}
