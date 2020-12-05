package com.example.jess.ui.community;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
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

public class CommunityFragment extends ListFragment implements SearchView.OnQueryTextListener {
    private static String TAG = CommunityFragment.class.getSimpleName();

    private CommunityViewModel communityViewModel ;
    private Context mContext;
    private List<Community> mAllCommunities = new ArrayList<>(Database.COMMUNITIES.values());
    private CommunityAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        communityViewModel =
                new ViewModelProvider(this).get(CommunityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_community, container, false);
        ListView listView = (ListView) root.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) root.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);

        SearchView searchView = root.findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        resetSearch();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }

        List<Community> filteredValues = new ArrayList<>(mAllCommunities);
        for (Community community : mAllCommunities) {
            if (!community.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(community);
            }
        }

        mAdapter = new CommunityAdapter(mContext, filteredValues);
        setListAdapter(mAdapter);

        return false;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Community item = (Community) l.getAdapter().getItem(position);
        Log.d("CommunityFragment", item.toString());
    }

    public void resetSearch() {
        mAdapter = new CommunityAdapter(mContext, mAllCommunities);
        setListAdapter(mAdapter);
    }
}