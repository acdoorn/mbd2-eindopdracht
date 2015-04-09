package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements
        MyListFragment.OnItemSelectedListener, RestaurantsFragment.OnItemSelectedListener {


    android.widget.ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
