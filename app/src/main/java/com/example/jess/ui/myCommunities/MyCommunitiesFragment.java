package com.example.jess.ui.myCommunities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.jess.models.Community;

import java.util.ArrayList;
import java.util.List;

public class MyCommunitiesFragment extends ListFragment {
    private static String TAG = MyCommunitiesFragment.class.getSimpleName();

    private MyCommunitiesViewModel myCommunitiesViewModel;
    private Context mContext;
    private MyCommunitiesAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myCommunitiesViewModel =
                new ViewModelProvider(this).get(MyCommunitiesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_communities, container, false);
        ListView listView = (ListView) root.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) root.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);

        List<Community> communities = new ArrayList<>(Database.SIGNED_IN_USER.getCommunities());
        mAdapter = new MyCommunitiesAdapter(mContext, communities);
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
        Community item = (Community) l.getAdapter().getItem(position);
        Log.d("MyCommunitiesFragment", item.toString());
    }
}