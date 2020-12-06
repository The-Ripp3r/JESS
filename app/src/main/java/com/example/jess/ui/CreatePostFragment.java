package com.example.jess.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.jess.Database;
import com.example.jess.R;
import com.example.jess.models.Community;
import com.example.jess.models.Post;
import com.example.jess.persistentcloudanchor.CloudAnchorActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class CreatePostFragment extends ListFragment {
    private static String TAG = CreatePostFragment.class.getSimpleName();

    private Context mContext;
    private CreatePostAdapter mAdapter;
    private Post post;
    private String chosenObj; // obj to be posted
    private String chosenTemplate; // texture for obj
    private LinearLayout templateContainer;
    private AppCompatButton createButton;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        root = inflater.inflate(R.layout.fragment_new_post, container, false);
        ListView listView = (ListView) root.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) root.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);

        List<Community> myCommunities = new ArrayList<>(Database.SIGNED_IN_USER.getCommunities());
        mAdapter = new CreatePostAdapter(mContext, myCommunities);
        setListAdapter(mAdapter);

        templateContainer = root.findViewById(R.id.choose_template_container);
        templateContainer.setVisibility(View.GONE);

        ImageView template1 = root.findViewById(R.id.template_1);
        ImageView template2 = root.findViewById(R.id.template_2);
        ImageView template3 = root.findViewById(R.id.template_3);

        template1.setOnClickListener(v -> {
            chosenTemplate = "lofi_texture.jpg";
            chosenObj = "cube.obj";
            template1.setAlpha(1f);
            template2.setAlpha(.5f);
            template3.setAlpha(.5f);
            createButton.setEnabled(true);
        });
        template2.setOnClickListener(v -> {
            chosenTemplate = "lofi_texture_2.png";
            chosenObj = "cube.obj";
            template1.setAlpha(.5f);
            template2.setAlpha(1f);
            template3.setAlpha(.5f);
            createButton.setEnabled(true);
        });
        template3.setOnClickListener(v -> {
            chosenTemplate = "rain_cloud_albedo.jpg";
            chosenObj = "rain_cloud.obj";
            template1.setAlpha(.5f);
            template2.setAlpha(.5f);
            template3.setAlpha(1f);
            createButton.setEnabled(true);
        });
        createButton = root.findViewById(R.id.new_post_create_button);
        createButton.setOnClickListener(this::onCreateClick);
        createButton.setEnabled((chosenObj != null || chosenTemplate != null) && post.getCommunity() != null);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        post = new Post(Database.SIGNED_IN_USER.getUsername(), "12/8/20", new LatLng(42.3578, -71.0935), "", "", "",null);
        post.setHoursAgo(0);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Community item = (Community) l.getAdapter().getItem(position);
        Log.d(TAG, item.toString());
    }

    private void onCreateClick(View v) {
        EditText title = (EditText) root.findViewById(R.id.new_post_title);
        post.setTitle(title.getText().toString());

        EditText description = (EditText) root.findViewById(R.id.new_post_description);
        post.setDescription(description.getText().toString());

        EditText musicLink = (EditText) root.findViewById(R.id.new_post_music_link);
        post.setMusicUrl(musicLink.getText().toString());

        Database.SIGNED_IN_USER.addPost(post);
        Database.NEARBY_POSTS.add(post);

        Intent intent = CloudAnchorActivity.newHostingIntent(getActivity());
        intent.putExtra("selectedObj", chosenObj);
        intent.putExtra("selectedTexture", chosenTemplate);
        startActivity(intent);
    }

    private class CreatePostAdapter extends ArrayAdapter<Community> {
        private final List<Community> communityList;

        public CreatePostAdapter(Context context, List<Community> list) {
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

            AppCompatButton selectButton = (AppCompatButton) listItem.findViewById(R.id.join_button);
            AppCompatButton unselectButton = (AppCompatButton) listItem.findViewById(R.id.leave_button);

            selectButton.setText("SELECT");
            selectButton.setOnClickListener(v ->{
                post.setCommunity(currentCommunity);
                templateContainer.setVisibility(View.VISIBLE);
                selectButton.setVisibility(View.GONE);
                unselectButton.setVisibility(View.VISIBLE);

            });
            unselectButton.setText("UNSELECT");
            unselectButton.setVisibility(View.GONE);
            unselectButton.setOnClickListener(v -> {
                post.setCommunity(null);
                templateContainer.setVisibility(View.GONE);
                selectButton.setVisibility(View.VISIBLE);
                unselectButton.setVisibility(View.GONE);
                createButton.setEnabled(false);
            });

            return listItem;
        }
    }
}
