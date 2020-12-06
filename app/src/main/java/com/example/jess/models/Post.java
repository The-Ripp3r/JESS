package com.example.jess.models;

import com.google.android.gms.maps.model.LatLng;

public class Post {
    public String username;
    public String datePosted;
    public LatLng latlng;
    public String title;
    public String description;
    public String musicUrl;
    public Community community;
    public int hoursAgo;
    public int numLikes;

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
        this.hoursAgo = (int) (Math.random() * (24));
        this.numLikes = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    public int getHoursAgo() {
        return hoursAgo;
    }

    public Community getCommunity() {
        return community;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void addLikes(int diff) {
        numLikes += diff;
    }

    public void setHoursAgo(int hours) {
        hoursAgo = hours;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public void setCommunity(Community community) {
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
