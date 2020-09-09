package com.senior.wiet.activities.map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.senior.wiet.R;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        context = getApplicationContext();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ImageView btnback = findViewById(R.id.back_buttonmap);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float latitude = AppSharedPreference.getInstance(context).getFdlat();
        float longitude = AppSharedPreference.getInstance(context).getFdlon();
        float rLatitude = (float) AppSharedPreference.getInstance(context).getRealLat();
        float rLongitude = (float) AppSharedPreference.getInstance(context).getRealLong();
        String restaurant_name = AppSharedPreference.getInstance(context).getFdstorename();
        String restaurant_location = AppSharedPreference.getInstance(context).getFdaddress();
        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(latitude, longitude);
        LatLng yourLocation = new LatLng(rLatitude,rLongitude);

        mMap.addMarker(new MarkerOptions()
                .position(yourLocation)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title(context.getString(R.string.your_location)));

        mMap.addMarker(new MarkerOptions()
                .position(location)
                .snippet(restaurant_location)
                .title(restaurant_name)).showInfoWindow();

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(location);
        builder.include(yourLocation);

        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 100);

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.animateCamera(cu);
            }
        });

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
