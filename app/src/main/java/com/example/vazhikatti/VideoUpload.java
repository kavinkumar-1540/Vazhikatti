package com.example.vazhikatti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoUpload extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText, playlistEditText;
    private Button selectVideoButton, uploadButton;
    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_upload);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        playlistEditText = findViewById(R.id.playlistEditText);
        selectVideoButton = findViewById(R.id.selectVideoButton);
        uploadButton = findViewById(R.id.uploadButton);

        selectVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVideo();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadVideo();
            }
        });
    }

    private void selectVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            videoUri = data.getData();
        }
    }

    private void uploadVideo() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String playlist = playlistEditText.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty() || playlist.isEmpty() || videoUri == null) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(videoUri.getPath());
        RequestBody requestFile = RequestBody.create(MediaType.parse("video/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("video", file.getName(), requestFile);
        RequestBody titlePart = RequestBody.create(MultipartBody.FORM, title);
        RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM, description);
        RequestBody playlistPart = RequestBody.create(MultipartBody.FORM, playlist);

        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.uploadVideo(body, titlePart, descriptionPart, playlistPart);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(VideoUpload.this, "Video uploaded successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VideoUpload.this, "Upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(VideoUpload.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
