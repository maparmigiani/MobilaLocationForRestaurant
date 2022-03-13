package com.example.diegohernandez_maiaraparmigiani_comp304sec003_lab4;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.diegohernandez_maiaraparmigiani_comp304sec003_lab4.databinding.ActivityRestaurantSpotBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RestaurantSpotActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityRestaurantSpotBinding binding;
    Button btnToggleMapType;
    boolean isSatelliteEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRestaurantSpotBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



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

        //Getting extras
        Intent intent = getIntent();
        String restaurantName  = intent.getStringExtra("RESTAURANT_NAME");
        String restaurantAddress  = intent.getStringExtra("RESTAURANT_ADDRESS");

        //Setting geocoder
        List<Address> addressList = null;
        Locale canadaLocale = new Locale("en","CA"); // this is for limiting the search to canada
        Geocoder geocoder = new Geocoder(getApplicationContext(),canadaLocale); // this is for using some features for searching places
        try {
            addressList = geocoder.getFromLocationName(restaurantAddress, 1); //returns a list that matches with the location name
            Log.i("MYLOG",String.valueOf(addressList.size()));
            double restaurantLatitude = addressList.get(0).getLatitude();
            double restaurantLongitud = addressList.get(0).getLongitude();

            // Add a marker in Sydney and move the camera
            LatLng restaurantSpot = new LatLng(restaurantLatitude, restaurantLongitud);

            mMap.addMarker(new MarkerOptions().position(restaurantSpot).title(restaurantName));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurantSpot));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(19));
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);

        } catch (Exception e) {
            Log.i("MYLOG",e.getMessage());
        }

        btnToggleMapType = findViewById(R.id.btnToggleType);

        btnToggleMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSatelliteEnabled){
                    isSatelliteEnabled = false;
                    mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL);
                }
                else {
                    isSatelliteEnabled = true;
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}