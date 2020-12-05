package com.example.jess.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Community> communities;
    private List<Post> posts;
    private String username;

    public User(List<Community> communities,
                List<Post> posts,
                String username) {
        this.communities = new ArrayList<>(communities);
        this.posts = new ArrayList<>(posts);
        this.username = username;
    }

    public void addCommunity(Community community) {
        if (communities == null) {
            communities = new ArrayList<>();
        }
        communities.add(community);
    }

    public void leaveCommunity(Community community) {
        if (communities != null) {
            communities.remove(community);
        }
    }

    public boolean inCommunity(Community community) {
        return communities.contains(community);
    }

    public List<Community> getCommunities() {
        return communities;
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
