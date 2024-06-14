package com.example.vazhikatti;

public class video {
    private String title;
    private String description;
    private String playlist;
    private String url;

    // Constructor
    public video(String title, String description, String playlist, String url) {
        this.title = title;
        this.description = description;
        this.playlist = playlist;
        this.url = url;
    }

    // Default Constructor
    public video() {
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}