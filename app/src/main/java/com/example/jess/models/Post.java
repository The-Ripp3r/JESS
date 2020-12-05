package com.example.jess.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Objects;

public class Post {
    public String username;
    public String datePosted;
    public LatLng latlng;
    public String title;
    public String description;
    public String musicUrl;
    public Community community;

    public Post(String username,
                String datePosted,
                LatLng latlng,
                String title,
                String description,
                String musicUrl,
                Community community) {
        this.username = username;
        this.datePosted = datePosted;
        this.latlng = latlng;
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
        this.community = community;
    }

    @Override
    public String toString() {
        return "Post{" +
                "username='" + username + '\'' +
                ", datePosted='" + datePosted + '\'' +
                ", latlng=" + latlng +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", community=" + community +
                '}';
    }
}
