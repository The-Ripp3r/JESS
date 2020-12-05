package com.example.jess.ui.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.jess.Database;
import com.example.jess.R;
import com.example.jess.models.Post;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.Optional;

public class PostInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;

    public PostInfoWindowAdapter(Context ctx) {
        context = ctx;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return renderWindow(marker);
    }

    private View renderWindow(Marker marker) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.post_info_window, null);
        Post post = (Post) marker.getTag();

        Log.d("infowindow",post.toString());
        ImageView audio = view.findViewById(R.id.map_post_audio_indicator);
        if (post.musicUrl != null && !post.musicUrl.isEmpty()) {
            audio.setBackgroundResource(R.drawable.ic_audio);
        }

        TextView title = view.findViewById(R.id.map_post_title);
        title.setText(post.title);

        TextView description = view.findViewById(R.id.map_post_description);
        description.setText(post.description);

        TextView author = view.findViewById(R.id.map_post_author);
        author.setText("by " + post.username);

        TextView community = view.findViewById(R.id.map_post_community);
        community.setText("in " + post.community.getName());

        TextView timeAgo = view.findViewById(R.id.map_post_time);
        timeAgo.setText("3hrs ago");
        return view;
    }
}
