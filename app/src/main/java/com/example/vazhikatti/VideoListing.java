package com.example.vazhikatti;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoListing extends AppCompatActivity {

    private Button allButton, playlistButton, favoritesButton;
    private RecyclerView videoRecyclerView;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_listing);

        allButton = findViewById(R.id.allButton);
        playlistButton = findViewById(R.id.playlistButton);
        favoritesButton = findViewById(R.id.favoritesButton);
        videoRecyclerView = findViewById(R.id.videoRecyclerView);

        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAllVideos();
            }
        });

        playlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPlaylistVideos();
            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchFavoriteVideos();
            }
        });
    }

    private void fetchAllVideos() {
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        Call<List<video>> call = apiService.getAllVideos();

        call.enqueue(new Callback<List<video>>() {
            @Override
            public void onResponse(Call<List<video>> call, Response<List<video>> response) {
                if (response.isSuccessful()) {
                    List<video> videos = response.body();
                    videoAdapter = new VideoAdapter(videos);
                    videoRecyclerView.setAdapter(videoAdapter);
                } else {
                    Toast.makeText(VideoListing.this, "Failed to load videos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<video>> call, Throwable t) {
                Toast.makeText(VideoListing.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchPlaylistVideos() {
        String playlistName = "your_playlist_name"; // Replace with actual playlist name

        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        Call<List<video>> call = apiService.getVideosByPlaylist(playlistName);

        call.enqueue(new Callback<List<video>>() {
            @Override
            public void onResponse(Call<List<video>> call, Response<List<video>> response) {
                if (response.isSuccessful()) {
                    List<video> videos = response.body();
                    videoAdapter = new VideoAdapter(videos);
                    videoRecyclerView.setAdapter(videoAdapter);
                } else {
                    Toast.makeText(VideoListing.this, "Failed to load playlist videos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<video>> call, Throwable t) {
                Toast.makeText(VideoListing.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchFavoriteVideos() {
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        Call<List<video>> call = apiService.getFavoriteVideos();

        call.enqueue(new Callback<List<video>>() {
            @Override
            public void onResponse(Call<List<video>> call, Response<List<video>> response) {
                if (response.isSuccessful()) {
                    List<video> videos = response.body();
                    videoAdapter = new VideoAdapter(videos);
                    videoRecyclerView.setAdapter(videoAdapter);
                } else {
                    Toast.makeText(VideoListing.this, "Failed to load favorite videos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<video>> call, Throwable t) {
                Toast.makeText(VideoListing.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
