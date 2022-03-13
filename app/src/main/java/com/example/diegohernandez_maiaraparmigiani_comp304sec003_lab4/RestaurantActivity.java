package com.example.diegohernandez_maiaraparmigiani_comp304sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    ArrayList<String> latinRestaurants = new ArrayList<>();
    ArrayList<String> greekRestaurants = new ArrayList<>();
    ArrayList<String> italianRestaurants = new ArrayList<>();
    ArrayList<String> koreanRestaurants = new ArrayList<>();
    ArrayList<String> japaneseRestaurants = new ArrayList<>();
    ArrayList<String> indianRestaurants = new ArrayList<>();
    ArrayList<String> caribbeanRestaurants = new ArrayList<>();
    ArrayList<String> restaurants = new ArrayList<>();
    ArrayList<String> cuisines;
    int restaurantIndex;

    TextView title;
    ListView lvRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        title = findViewById(R.id.lblRestaurantActTitle);
        lvRestaurants = findViewById(R.id.lvRestaurants);

        //Getting extras
        Intent intent = getIntent();
        restaurantIndex = intent.getIntExtra("INDEX", -1);
        cuisines = (ArrayList<String>) getIntent().getSerializableExtra("CUISINES_ARRAY");

        //filling all restaurants arrays
        fillArrays();

        //Log.i("MYLOG",String.valueOf(restaurantIndex));
        try {
            title.setText(cuisines.get(restaurantIndex));
            //Log.i("MYLOG","succeeded"+cuisines);
            switch (restaurantIndex){
                case 0: restaurants = latinRestaurants;
                break;
                case 1: restaurants = greekRestaurants;
                    break;
                case 2: restaurants = italianRestaurants;
                    break;
                case 3: restaurants = koreanRestaurants;
                    break;
                case 4: restaurants = japaneseRestaurants;
                    break;
                case 5: restaurants = indianRestaurants;
                    break;
                default: restaurants = caribbeanRestaurants;
                    break;

            }
            //Toast.makeText(getApplicationContext(),cuisines.get(restaurantIndex),Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.i("MYLOG","error:"+e.getMessage());
        }

        //Setting listview
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,restaurants);
        lvRestaurants.setAdapter(arrayAdapter);

        lvRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                intent = new Intent(getApplicationContext(), RestaurantSpotActivity.class);
                intent.putExtra("RESTAURANTS",restaurants);
                intent.putExtra("RESTAURANT_INDEX",i);
                intent.putExtra("RESTAURANT_TYPE",restaurantIndex);

                //Toast.makeText(getApplicationContext(),cuisines.get(i),Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }

    private  void fillArrays(){
        //filling latin restaurants
        latinRestaurants.add("latRes1");
        latinRestaurants.add("latRes2");
        latinRestaurants.add("latRes3");
        latinRestaurants.add("latRes4");
        latinRestaurants.add("latRes5");

        //filling greeks restaurants
        greekRestaurants.add("greek1");
        greekRestaurants.add("greek2");
        greekRestaurants.add("greek3");
        greekRestaurants.add("greek4");
        greekRestaurants.add("greek5");

        //filling latin restaurants
        italianRestaurants.add("italian");
        italianRestaurants.add("italian");
        italianRestaurants.add("italian");
        italianRestaurants.add("italian");
        italianRestaurants.add("italian");

        //filling latin restaurants
        koreanRestaurants.add("korean");
        koreanRestaurants.add("korean");
        koreanRestaurants.add("korean");
        koreanRestaurants.add("korean");
        koreanRestaurants.add("korean");

        //filling latin restaurants
        japaneseRestaurants.add("japanese");
        japaneseRestaurants.add("japanese");
        japaneseRestaurants.add("japanese");
        japaneseRestaurants.add("japanese");
        japaneseRestaurants.add("japanese");

        //filling latin restaurants
        indianRestaurants.add("indian");
        indianRestaurants.add("indian");
        indianRestaurants.add("indian");
        indianRestaurants.add("indian");
        indianRestaurants.add("indian");

        //filling latin restaurants
        caribbeanRestaurants.add("caribbean");
        caribbeanRestaurants.add("caribbean");
        caribbeanRestaurants.add("caribbean");
        caribbeanRestaurants.add("caribbean");
        caribbeanRestaurants.add("caribbean");

    }
}