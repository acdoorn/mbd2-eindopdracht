package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends ActionBarActivity implements
        RestaurantsFragment.OnItemSelectedListener, RestaurantsFragment.OnTaskCompleted  {


    // Create a List for the restaurants.
    List<Restaurant> restaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Need to check if Activity has been switched to landscape mode
        // If yes, finished and go back to the start Activity
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_restaurants);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            RestaurantsFragment restaurantsFragment = (RestaurantsFragment) getFragmentManager()
                    .findFragmentById(R.id.restaurantsFragment);
        }
    }

    @Override
    public void onRssItemSelected(Restaurant selectedItem) {
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
            // use data
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra("restaurantId", selectedItem.getId());
            intent.putExtra("restaurantName", selectedItem.getName());
            if(selectedItem.getTelephone() != null) {
                intent.putExtra("restaurantTelephone", selectedItem.getTelephone());
            }
            if(selectedItem.getRating() != 0) {
                intent.putExtra("restaurantRating", selectedItem.getRating());
            }
            if(selectedItem.getStreet() != null) {
                intent.putExtra("restaurantStreet", selectedItem.getStreet());
            }
            if(selectedItem.getZipcode() != null) {
                intent.putExtra("restaurantZipcode", selectedItem.getZipcode());
            }
            if(selectedItem.getLat() != 0) {
                intent.putExtra("restaurantLatitude", selectedItem.getLat());
            }
            if(selectedItem.getLng() != 0) {
                intent.putExtra("restaurantLongitude", selectedItem.getLng());
            }

            if(selectedItem.getTelephone() != null) {
                intent.putExtra("restaurantTelephone", selectedItem.getTelephone());
            }
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Log the item's position and contents
        // to the console in Debug
        if(item.getItemId() == R.id.menu_item_share) {
            MenuFragment fragment = (MenuFragment) getFragmentManager()
                    .findFragmentById(R.id.menuFragment);
            if (fragment != null && fragment.isInLayout()) {
            } else {
                Intent intent = new Intent(getApplicationContext(),
                        MenuActivity.class);
                startActivity(intent);

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskCompleted(String jsonString) {
        ListView lv = (ListView)findViewById(android.R.id.list);

        // Add categories. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.

        try {
            System.out.println(jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray json = (JSONArray) jsonObject.get("results");
            for(Integer i = 0; i < json.length(); i++) {
                JSONObject g = (JSONObject) json.get(i);
                Integer id = (Integer) g.get("id");
                String name = (String) g.get("name");
                Restaurant restaurant = new Restaurant(id, name);
                String telephone = "";
                Integer rating = null;
                String street = "";
                String zipcode = "";
                if(!g.isNull("telephone")) {
                    restaurant.setTelephone((String) g.get("telephone"));
                }
                if(!g.isNull("rating")) {
                    restaurant.setRating((Integer) g.get("rating"));
                }
                if(!g.isNull("address")) {
                    JSONObject address = (JSONObject) g.get("address");
                    restaurant.setStreet((String) address.get("street"));
                    restaurant.setZipcode((String) address.get("zipcode"));
                    restaurant.setCity((String) address.get("city"));
                }
                if(!g.isNull("geolocation")) {
                    JSONObject geolocation = (JSONObject) g.get("geolocation");
                    restaurant.setLat((Double) geolocation.get("latitude"));
                    restaurant.setLng((Double) geolocation.get("longitude"));
                }
                restaurantList.add(restaurant);
            }
        }
        catch (JSONException e) {
            System.out.println("JSONException");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.row);
        for (Restaurant r : restaurantList) {
            adapter.add(r.getName());
        }
        // Set the ArrayAdapter as the ListView's adapter.
        lv.setAdapter( adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                System.out.println("Linking to DetailView");
                onRssItemSelected(restaurantList.get(arg2));
            }
        });
    }
}