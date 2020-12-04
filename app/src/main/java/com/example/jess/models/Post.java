package com.example.jess.models;

import com.google.android.gms.maps.model.LatLng;

public class Post {
    public String username;
    public String datePosted;
    public LatLng latlng;
    public String title;
    public String description;
    public String musicUrl;

    public Post(String username,
                String datePosted,
                LatLng latlng,
                String title,
                String description,
                String musicUrl) {
        this.username = username;
        this.datePosted = datePosted;
        this.latlng = latlng;
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
    }
}
