package com.example.jess.models;

import java.lang.reflect.Array;
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
        community.changeNumMembers(1);
        communities.add(community);
    }

    public void leaveCommunity(Community community) {
        if (communities != null) {
            community.changeNumMembers(-1);
            communities.remove(community);
        }
    }

    public void addPost(Post post) {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(post);
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
