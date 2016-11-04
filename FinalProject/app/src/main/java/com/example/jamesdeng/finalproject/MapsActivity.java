package com.example.jamesdeng.finalproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

// Need broadcast receiver here to get lat and lng of current location from LocationActivity to MapsActivity
//        LatLng currentLocation = new LatLng(lat,lng);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String lat = pref.getString("lat", "");
        String lng = pref.getString("lng", "");

        Float latInt= Float.parseFloat(lat);
        Float lngInt= Float.parseFloat(lng);

        LatLng currentLocation = new LatLng(latInt,lngInt);

        Marker markerCurrentLocation = mMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker in Resorts World Sentosa").snippet("This is Resorts World Sentosa"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null);
    }
}
