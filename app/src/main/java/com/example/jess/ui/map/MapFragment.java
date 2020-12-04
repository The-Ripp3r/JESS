package com.example.jess.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jess.Database;
import com.example.jess.R;
import com.example.jess.helpers.LocationPermissionHelper;
import com.example.jess.models.Post;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapViewModel mapViewModel;
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        initializeMap();

        return root;
    }

    private void initializeMap() {
        if (mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(LocationPermissionHelper.hasLocationPermission(getActivity())) {
            mMap.setMyLocationEnabled(true);
        } else {
            LocationPermissionHelper.requestLocationPermission(getActivity());
        }

        for (Post post : Database.NEARBY_POSTS) {
            mMap.addMarker(new MarkerOptions()
                    .position(post.latlng)
                    .title(post.title)
                    .snippet(post.description + "\n\n -" + post.username + " " + post.datePosted));
        }

        mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(15);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Database.NEARBY_POSTS[0].latlng));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(requestCode, permissions, results);
        if (!LocationPermissionHelper.hasLocationPermission(getActivity())) {
            // Use toast instead of snackbar here since the activity will exit.
            Toast.makeText(getActivity(), "Location permission is needed to run this application", Toast.LENGTH_LONG)
                    .show();
            if (!LocationPermissionHelper.shouldShowRequestPermissionRationale(getActivity())) {
                // Permission denied with checking "Do not ask again".
                LocationPermissionHelper.launchPermissionSettings(getActivity());
            }
            getActivity().finish();
        } else {
            mMap.setMyLocationEnabled(true);
        }
    }


}