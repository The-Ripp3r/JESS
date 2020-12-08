package com.example.jess;

import com.example.jess.models.Community;
import com.example.jess.models.Post;
import com.example.jess.models.User;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Dummy data for app to demonstrate minimum functionality
public class Database {
    public static final Map<String, Community> COMMUNITIES = Map.of(
            "lofi", new Community("lofi", "Come chill", 3141),
            "Wholesome Memes", new Community("Wholesome Memes", "Dedicated to wholesomeness to uplift and support others!", 4170),
            "Lore", new Community("Lore", "Stories of the Land", 449),
            "Bunnies at MIT", new Community("Bunnies at MIT", "Spotting the bunnies", 103),
            "Cozy Places", new Community("Cozy Places", "Warm places to warm your soul", 3141),
            "Self Improvement", new Community("Self Improvement", "Make the most of yourself - for that is all there is of you.", 99),
            "Memory Lane", new Community("Memory Lane", "Dedicated to feeling that bittersweet nostalgia while reminiscing the past", 450));

    public static final List<Post> POSTS = List.of(
            new Post("yinj", "12/4/20", new LatLng(42.3578, -71.0933), "finals mood", "its that time of the year asdlkjfwaejrasfasfasdfawerqwersafaslks", "https://youtu.be/lTRiuFIWV54", COMMUNITIES.get("lofi")),
            new Post("jack", "12/5/20", new LatLng(42.3581, -71.0950), "in my feels", "this is how i feel", null, COMMUNITIES.get("lofi")),
            new Post("harry", "12/5/20", new LatLng(42.3591, -71.0947), "my soul", "i left it in stud 5", "https://youtu.be/7k9FBNqGhBg", COMMUNITIES.get("lofi")));

    public static final User SIGNED_IN_USER = new User(List.of(COMMUNITIES.get("lofi")), List.of(POSTS.get(0)), "yinj");

    public static final List<User> USERS = List.of(
            SIGNED_IN_USER);

    public static final List<Post> NEARBY_POSTS = new ArrayList<> (List.of(
            POSTS.get(0),
            POSTS.get(1),
            POSTS.get(2)));
}
