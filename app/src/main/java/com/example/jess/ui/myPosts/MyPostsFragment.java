package com.example.jess.ui.myPosts;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.jess.Database;
import com.example.jess.R;
import com.example.jess.models.Post;

import java.util.ArrayList;
import java.util.List;

public class MyPostsFragment extends ListFragment {
    private static String TAG = MyPostsFragment.class.getSimpleName();

    private MyPostsViewModel myPostsViewModel;
    private Context mContext;
    private MyPostsAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myPostsViewModel =
                new ViewModelProvider(this).get(MyPostsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_posts, container, false);
        ListView listView = (ListView) root.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) root.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);

        List<Post> posts = new ArrayList<>(Database.SIGNED_IN_USER.getPosts());
        mAdapter = new MyPostsAdapter(mContext, posts);
        setListAdapter(mAdapter);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Post item = (Post) l.getAdapter().getItem(position);
        Log.d("MyPostsFragment", item.toString());
    }
}