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

    ArrayList<Restaurant> latinRestaurants = new ArrayList<>();
    ArrayList<Restaurant> greekRestaurants = new ArrayList<>();
    ArrayList<Restaurant> italianRestaurants = new ArrayList<>();
    ArrayList<Restaurant> koreanRestaurants = new ArrayList<>();
    ArrayList<Restaurant> japaneseRestaurants = new ArrayList<>();
    ArrayList<Restaurant> indianRestaurants = new ArrayList<>();
    ArrayList<Restaurant> caribbeanRestaurants = new ArrayList<>();
    ArrayList<Restaurant> restaurants = new ArrayList<>();
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
            title.setText(cuisines.get(restaurantIndex).toString());
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
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.listview_row,R.id.textView,restaurants);
        lvRestaurants.setAdapter(arrayAdapter);

        lvRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                intent = new Intent(getApplicationContext(), RestaurantSpotActivity.class);
                intent.putExtra("RESTAURANT_ADDRESS",restaurants.get(i).getAddress());
                intent.putExtra("RESTAURANT_NAME",restaurants.get(i).getName());
                Toast.makeText(getApplicationContext(),restaurants.get(i).getAddress(),Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }

    private  void fillArrays(){
        //filling latin restaurants
        latinRestaurants.add(new Restaurant("MAIZ Yonge","3220 Yonge St, Toronto, ON M4N 2L2"));
        latinRestaurants.add(new Restaurant("Limon Lounge Restaurant","1089 St Clair Ave W, Toronto, ON M6E 1A8"));
        latinRestaurants.add(new Restaurant("Toronto Latino","1786 Eglinton Ave W, York, ON M6E 2H6"));
        latinRestaurants.add(new Restaurant("2 Amigos Latinos Restaurant","1650 Jane St Unit 1, North York, ON M9N 2R8"));
        latinRestaurants.add(new Restaurant("Cuscatlan","3232 Steeles Ave W Unit 8, Concord, ON L4K 3B8"));

        //filling greeks restaurants
        greekRestaurants.add(new Restaurant("Krystos Modern Greek Cuisine", "3200 Dufferin St #22, Toronto, ON M6A 3B2"));
        greekRestaurants.add(new Restaurant("Jimmy The Greek", "3401 Dufferin Street Unit #FC12, North York, ON M6A 2T9"));
        greekRestaurants.add(new Restaurant("Souvlaki Guys", "6 Fairlawn Ave, Toronto, ON M5M 1S7"));
        greekRestaurants.add(new Restaurant("Mykonos Mediterranean Grill", "881 Yonge St, Toronto, ON M4W 2H2"));
        greekRestaurants.add(new Restaurant("Sofra Forest Hill", "431 Spadina Rd, Toronto, ON M5P 2W3"));


        //filling latin restaurants
        italianRestaurants.add(new Restaurant("Pazza Pazza", "1007 Eglinton Ave W, York, ON M6C 2C7"));
        italianRestaurants.add(new Restaurant("7 Numbers EGLINTON", "516 Eglinton Ave W, Toronto, ON M5N 1A5"));
        italianRestaurants.add(new Restaurant("Agape Pasta Fusione", "3200 Dufferin St, North York, ON M6A 3B2"));
        italianRestaurants.add(new Restaurant("Padella Italian Eatery", "1967 Avenue Rd, North York, ON M5M 4A3"));
        italianRestaurants.add(new Restaurant("La Vecchia Restaurant Uptown", "2405 Yonge St A, Toronto, ON M4P 2E7"));

        //filling latin restaurants
        koreanRestaurants.add(new Restaurant("Bi Bim Bap", "950 Eglinton Ave W, Toronto, ON M6C 2C2"));
        koreanRestaurants.add(new Restaurant("Seoul House Restaurant", "3220 Dufferin St, North York, ON M6A 2T3"));
        koreanRestaurants.add(new Restaurant("Korean Village Restaurant", "628 Bloor St W, Toronto, ON M6G 1K7"));
        koreanRestaurants.add(new Restaurant("Dasoni (authentic Korean cuisine)", "630 St Clair Ave W, Toronto, ON M6C 1A9"));
        koreanRestaurants.add(new Restaurant("Yummy Korean Food Restaurant", "Bloor St W, CA ON Toronto, M6G 1L4"));

        //filling latin restaurants
        japaneseRestaurants.add(new Restaurant("Miyako Sushi", "572 Marlee Ave, North York, ON M6B 3J3"));
        japaneseRestaurants.add(new Restaurant("Healthy Sushi", "3027 Bathurst St, North York, ON M6B 3B5"));
        japaneseRestaurants.add(new Restaurant("Tokyo Sushi", "971 Eglinton Ave W, York, ON M6C 2C4"));
        japaneseRestaurants.add(new Restaurant("Yu Ki", "3259 Dufferin St, North York, ON M6A 2T2"));
        japaneseRestaurants.add(new Restaurant("TORA", "3401 Dufferin St #612B, North York, ON M6A 2T9"));

        //filling latin restaurants
        indianRestaurants.add(new Restaurant("Shahi The Royal Roti", "349 Marlee Ave, North York, ON M6B 3H9"));
        indianRestaurants.add(new Restaurant("The Great Maratha", "965 Eglinton Ave W, Toronto, ON M6C 2C4"));
        indianRestaurants.add(new Restaurant("Roti King West Indian Restaurant", "1688 Eglinton Ave W, York, ON M6E 2H5"));
        indianRestaurants.add(new Restaurant("Javitri Indian Cuisine", "1708 Eglinton Ave W, York, ON M6E 2H5"));
        indianRestaurants.add(new Restaurant("Amaya Express", "572 Marlee Ave, North York, ON M6B 3J3"));

        //filling latin restaurants
        caribbeanRestaurants.add(new Restaurant("The Entertainment Kitchen", "1561 Eglinton Ave W, York, ON M6E 2G9"));
        caribbeanRestaurants.add(new Restaurant("CARIB21", "2039 Eglinton Ave W, York, ON M6E 2K4"));
        caribbeanRestaurants.add(new Restaurant("RAP'S", "1541 Eglinton Ave W, York, ON M6E 2G7"));
        caribbeanRestaurants.add(new Restaurant("Judy's Island Grill Toronto", "1720 Eglinton Ave W, York, ON M6E 2H5"));
        caribbeanRestaurants.add(new Restaurant("ScottHill Caribbean Cuisine", "1943 Avenue Rd, North York, ON M5M 4A2"));

    }
}