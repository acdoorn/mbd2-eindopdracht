package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity implements
        MyListFragment.OnItemSelectedListener, RestaurantsFragment.OnItemSelectedListener, MyListFragment.OnTaskCompleted {

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
    public void onRssItemSelected() {
        RestaurantsFragment fragment = (RestaurantsFragment) getFragmentManager()
                .findFragmentById(R.id.restaurantsFragment);
        if (fragment != null && fragment.isInLayout()) {
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    RestaurantsActivity.class);
            startActivity(intent);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onTaskCompleted() {
        ArrayAdapter<String> adapter;
        ListView lv = getListView();
        // Create and populate a List of planet names.
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        List<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );
        // Create ArrayAdapter using the planet list.
        adapter = new ArrayAdapter<String>(this, R.layout.row, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        adapter.add( "Ceres" );
        adapter.add( "Pluto" );
        adapter.add( "Haumea" );
        adapter.add( "Makemake" );
        adapter.add( "Eris" );

        // Set the ArrayAdapter as the ListView's adapter.
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                System.out.println("HOERE");
//                MyListFragment.OnItemSelectedListener();
            }
        });
        adapter.notifyDataSetChanged();
    }
}
