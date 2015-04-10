package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

public class RestaurantsActivity extends ActionBarActivity implements
        RestaurantsFragment.OnItemSelectedListener {

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
    public void onRssItemSelected() {
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
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
        MenuFragment fragment = (MenuFragment) getFragmentManager()
                .findFragmentById(R.id.menuFragment);
        if (fragment != null && fragment.isInLayout()) {
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    MenuActivity.class);
            startActivity(intent);

        }
        return true;
    }
}