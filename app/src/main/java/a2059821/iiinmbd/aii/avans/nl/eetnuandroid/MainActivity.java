package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements
        MyListFragment.OnItemSelectedListener, RestaurantsFragment.OnItemSelectedListener, MyListFragment.OnTaskCompleted {



    List<String> categoryList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onRssItemSelected(String selectedItem) {
        RestaurantsFragment fragment = (RestaurantsFragment) getFragmentManager()
                .findFragmentById(R.id.restaurantsFragment);
        if (fragment != null && fragment.isInLayout()) {
        }
        else {
            Intent intent = new Intent(getApplicationContext(),
                    RestaurantsActivity.class);
            // Old method of passing tag
//            intent.putExtra("tag", selectedItem);
            SharedPreferences sharedPref = this.getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.saved_tag), selectedItem);
            editor.commit();
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onTaskCompleted(String jsonString) {
        ArrayAdapter<String> adapter;
        ListView lv = (ListView)findViewById(android.R.id.list);
        // Create ArrayAdapter using the category list.
        adapter = new ArrayAdapter<String>(this, R.layout.row, categoryList);

        // Add categories. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.

        try {
            System.out.println(jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray json = (JSONArray) jsonObject.get("results");
            for(Integer i = 0; i < json.length(); i++) {
                JSONObject g = (JSONObject) json.get(i);
                String name = (String) g.get("id");
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
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
                System.out.println("Linking to RestaurantsView");
                onRssItemSelected(categoryList.get(arg2));
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRssItemSelected(Restaurant selectedItem) {

    }
}
