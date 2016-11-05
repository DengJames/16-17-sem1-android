package com.example.jamesdeng.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;


public class LocationActivity extends Activity implements
        com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    MyBroadcastReceiver myBroadcastReceiver;
    IntentFilter myIntentFilter;


    SharedPreferences pref;
    String shareLat = "shareLat";
    String shareLng = "shareLng";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(12000);
        mLocationRequest.setFastestInterval(6000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }


    @Override
    public void onStart(){
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop(){
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onPause(){
        super.onPause();
        LocationServices.FusedLocationApi.removeLocationUpdates (mGoogleApiClient, this);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                PendingResult<Status> pendingResult =
                        LocationServices.FusedLocationApi.requestLocationUpdates(

                                mGoogleApiClient, mLocationRequest, this);
            }

        }
    }

    @Override
    public void onConnected(Bundle  bundle) {


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            PendingResult<Status> pendingResult =
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }


    }

    Location mCurrentLocation;

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        String mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        if (mCurrentLocation != null) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());

            String lng = String.valueOf(mCurrentLocation.getLongitude());

            Float latInt= Float.parseFloat(lat);
            Float lngInt= Float.parseFloat(lng);

            pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            SharedPreferences.Editor editor = pref.edit();
            editor.putFloat(shareLat, latInt);
            editor.putFloat(shareLng, lngInt);
            editor.commit();




           // Toast.makeText(this,lat+" "+lng, Toast.LENGTH_LONG).show();
            //   g e t   t h e   l o c a t i o n   w i t h   a c c u r a c y   a n d   a l s o   p r o v i d e r,   u s i n g
            // mCurrentLocation.getAccuracy()
            // mCurrentLocation.getProvider());
        }
    }

    public void toMap(View view) {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }
}

