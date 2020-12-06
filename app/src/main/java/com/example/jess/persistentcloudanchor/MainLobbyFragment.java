package com.example.jess.persistentcloudanchor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.jess.R;
import com.example.jess.common.helpers.DisplayRotationHelper;
import com.google.android.material.button.MaterialButton;

public class MainLobbyFragment extends Fragment {

    private DisplayRotationHelper displayRotationHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.main_lobby, container, false);
        displayRotationHelper = new DisplayRotationHelper(requireActivity());
        AppCompatButton createPostButton = root.findViewById(R.id.host_button);
        createPostButton.setOnClickListener((view) -> Navigation.findNavController(view).navigate(R.id.navigation_create_post));
//        AppCompatButton hostButton = root.findViewById(R.id.host_button); // aka create post
//        hostButton.setOnClickListener((view) -> onHostButtonPress());
        AppCompatButton resolveButton = root.findViewById(R.id.begin_resolve_button); // find a post
        resolveButton.setOnClickListener((view) -> onResolveButtonPress());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        displayRotationHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        displayRotationHelper.onPause();
    }

//    private void onHostButtonPress() {
//        Intent intent = CloudAnchorActivity.newHostingIntent(getActivity());
//        startActivity(intent);
//    }

    /** Callback function invoked when the Resolve Button is pressed. */
    private void onResolveButtonPress() {
        Intent intent = ResolveAnchorsLobbyActivity.newIntent(getActivity());
        startActivity(intent);
    }
}
