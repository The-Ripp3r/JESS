package com.example.jess.ui.myCommunities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.jess.Database;
import com.example.jess.R;
import com.example.jess.models.Community;

import java.util.List;

public class MyCommunitiesAdapter extends ArrayAdapter<Community> {
    private final Context mContext;
    private final List<Community> communityList;

    public MyCommunitiesAdapter(Context context, List<Community> list) {
        super(context, 0, list);
        mContext = context;
        communityList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.community_list_item, parent, false);
        }

        Community currentCommunity = communityList.get(position);
        TextView name = (TextView) listItem.findViewById(R.id.community_name);
        name.setText(currentCommunity.getName() + " (" + currentCommunity.getNumMembers() + " members)");

        TextView description = (TextView) listItem.findViewById(R.id.community_description);
        description.setText(currentCommunity.getDescription());

        AppCompatButton joinButton = (AppCompatButton) listItem.findViewById(R.id.join_button);
        AppCompatButton leaveButton = (AppCompatButton) listItem.findViewById(R.id.leave_button);
        if (!Database.SIGNED_IN_USER.inCommunity(currentCommunity)) {
            leaveButton.setVisibility(View.GONE);
        } else {
            joinButton.setVisibility(View.GONE);
        }
        joinButton.setOnClickListener(v -> {
            Database.SIGNED_IN_USER.addCommunity(currentCommunity);
            joinButton.setVisibility(View.GONE);
            leaveButton.setVisibility(View.VISIBLE);
        });
        leaveButton.setOnClickListener(v -> {
            Database.SIGNED_IN_USER.leaveCommunity(currentCommunity);
            joinButton.setVisibility(View.VISIBLE);
            leaveButton.setVisibility(View.GONE);
        });

        return listItem;
    }
}
