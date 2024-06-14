package com.example.vazhikatti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;
public class Verification extends AppCompatActivity  {
    private EditText otpEditText, usernameEditText, passwordEditText;
    private Button verifyButton;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        otpEditText = findViewById(R.id.otpEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        verifyButton = findViewById(R.id.verifyButton);

        mobile = getIntent().getStringExtra("mobile");

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp();
            }
        });
    }

    private void verifyOtp() {
        String otp = otpEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (otp.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.verify(mobile, otp, username, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Verification.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Verification.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Verification.this, "Verification failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Verification.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
