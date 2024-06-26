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


public class LoginActivity extends AppCompatActivity {
    private EditText mobileEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileEditText = findViewById(R.id.mobileEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String mobile = mobileEditText.getText().toString().trim();

        if (mobile.isEmpty()) {
            mobileEditText.setError("Mobile number is required");
            mobileEditText.requestFocus();
            return;
        }

        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.login(mobile);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "OTP sent to mobile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, Verification.class);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
