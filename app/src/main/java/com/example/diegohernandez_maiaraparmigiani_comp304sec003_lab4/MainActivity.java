package com.example.diegohernandez_maiaraparmigiani_comp304sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> cuisines = new ArrayList<>();
    ListView  lvCuisines;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cuisines.add("Latin");
        cuisines.add("Mediterranean");
        cuisines.add("Italian");
        cuisines.add("Korean");
        cuisines.add("Japanese");
        cuisines.add("Indian");
        cuisines.add("Caribbean");

        lvCuisines = findViewById(R.id.lvCuisines);

        //Setting listview
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,cuisines);
        lvCuisines.setAdapter(arrayAdapter);
        lvCuisines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                //Setting geocoder
                List<Address> addressList = null;
                Locale canadaLocale = new Locale("en","CA"); // this is for limiting the search to canada
                Geocoder geocoder = new Geocoder(getApplicationContext(),canadaLocale); // this is for using some features for searching places
                try {
                    addressList = geocoder.getFromLocationName("3220 Yonge St, Toronto, ON M4N 2L2", 1); //returns a list that matches with the location name
                    Log.i("MYLOG",String.valueOf(addressList.size()));

                } catch (Exception e) {
                    Log.i("MYLOG",e.getMessage());
                }

                //getting item position and filtering which activity to start
                Intent intent;
                intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                intent.putExtra("CUISINES_ARRAY",cuisines);
                intent.putExtra("INDEX",i);
                //Toast.makeText(getApplicationContext(),cuisines.get(i),Toast.LENGTH_SHORT).show();


                startActivity(intent);

            }
        });
    }
}