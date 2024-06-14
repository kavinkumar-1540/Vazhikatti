package com.example.vazhikatti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText mobileEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mobileEditText = findViewById(R.id.mobileEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String mobile = mobileEditText.getText().toString().trim();

        if (mobile.isEmpty()) {
            mobileEditText.setError("Mobile number is required");
            mobileEditText.requestFocus();
            return;
        }

        ApiService apiService = ApiService.retrofit.create(ApiService.class);

        Map<String, String> body = new HashMap<>();
        body.put("mobile", mobile);

        // Log the request body
        Log.d("API Request", "registerUser: " + new Gson().toJson(body));
        Call<ResponseBody> call = apiService.register(mobile);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Register.this, "OTP sent to mobile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Verification.class);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
