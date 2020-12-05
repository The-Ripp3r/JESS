package com.example.jess.ui.myPosts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jess.Database;
import com.example.jess.R;
import com.example.jess.models.Post;

import java.util.List;

public class MyPostsAdapter extends ArrayAdapter<Post> {
    private final Context mContext;
    private final List<Post> postList;

    public MyPostsAdapter(Context context, List<Post> list) {
        super(context, 0, list);
        mContext = context;
        postList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.post_list_item, parent, false);
        }

        Post currentPost = postList.get(position);
        TextView name = (TextView) listItem.findViewById(R.id.post_title);
        name.setText(currentPost.getTitle());

        TextView subtitle = (TextView) listItem.findViewById(R.id.post_subtitle);
        String username = currentPost.getUsername();
        String subtitleString = "Created by " +
                (username.equals(Database.SIGNED_IN_USER.getUsername()) ? "you" : username) +
                " in " + currentPost.getCommunity().getName() + ", " +
                String.valueOf(currentPost.getHoursAgo()) + " hours ago";
        subtitle.setText(subtitleString);

        TextView stats = (TextView) listItem.findViewById(R.id.post_stats);
        stats.setText(String.valueOf(currentPost.getNumLikes()) + " likes");

        TextView description = (TextView) listItem.findViewById(R.id.post_description);
        description.setText(currentPost.getDescription());

        return listItem;
    }
}
