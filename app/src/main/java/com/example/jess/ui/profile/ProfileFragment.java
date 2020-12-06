package com.example.jess.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.jess.ARActivity;
import com.example.jess.Database;
import com.example.jess.R;
import com.example.jess.persistentcloudanchor.CloudAnchorActivity;
import com.example.jess.persistentcloudanchor.MainLobbyActivity;
import com.example.jess.ui.myCommunities.MyCommunitiesFragment;
import com.example.jess.ui.myPosts.MyPostsFragment;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView helloUser = root.findViewById(R.id.hello_user);
        helloUser.setText("Hello " + Database.SIGNED_IN_USER.getUsername());
        root.findViewById(R.id.camera_button).setOnClickListener(this::startCamera);
        root.findViewById(R.id.my_communities_button).setOnClickListener(this::showMyCommunities);
        root.findViewById(R.id.my_posts_button).setOnClickListener(this::showMyPosts);
        return root;
    }

    public void startCamera(View view) {
        //get transferred to making post UI
        Intent intent = new Intent(ProfileFragment.this.getActivity(), MainLobbyActivity.class);
        startActivity(intent);
    }

    public void showMyCommunities(View view) {
        Fragment fragment = new MyCommunitiesFragment();
        Navigation.findNavController(view).navigate(R.id.navigation_my_communities);
        //replaceFragment(fragment);
    }

    public void showMyPosts(View view) {
        Fragment fragment = new MyPostsFragment();
        Navigation.findNavController(view).navigate(R.id.navigation_my_posts);
        //replaceFragment(fragment);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
