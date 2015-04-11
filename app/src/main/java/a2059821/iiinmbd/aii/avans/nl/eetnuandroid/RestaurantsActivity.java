package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
    List<String> restaurantList = new ArrayList<String>();

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
    public void onRssItemSelected(String selectedItem) {
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
            // use data
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
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

        ArrayAdapter<String> adapter;
        ListView lv = (ListView)findViewById(android.R.id.list);
        // Create ArrayAdapter using the category list.
        adapter = new ArrayAdapter<String>(this, R.layout.row, restaurantList);

        // Add categories. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.

        try {
            System.out.println(jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray json = (JSONArray) jsonObject.get("results");
            for(Integer i = 0; i < json.length(); i++) {
                JSONObject g = (JSONObject) json.get(i);
                String name = (String) g.get("name");
                adapter.add(name);
            }
        }
        catch (JSONException e) {
            System.out.println("JSONException");
        }

        // Set the ArrayAdapter as the ListView's adapter.
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                System.out.println("Linking to DetailView");
                onRssItemSelected(restaurantList.get(arg2));
            }
        });
        adapter.notifyDataSetChanged();
    }
}