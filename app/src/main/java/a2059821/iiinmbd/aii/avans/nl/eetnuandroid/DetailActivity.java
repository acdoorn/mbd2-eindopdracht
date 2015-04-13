package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Need to check if Activity has been switched to landscape mode
        // If yes, finished and go back to the start Activity
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            DetailFragment detailFragment = (DetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);
            TextView labelInfo = (TextView) findViewById(R.id.labelInfo);
            TextView textStreet = (TextView) findViewById(R.id.textStreet);
            TextView textZipcode = (TextView) findViewById(R.id.textZipcode);
            TextView textCity = (TextView) findViewById(R.id.textCity);
            Button callButton = (Button) findViewById(R.id.callButton);

            labelInfo.setText("Restaurant "+extras.getString("restaurantName"));
            if(extras.getString("restaurantTelephone") != null) {
                callButton.setText(extras.getString("restaurantTelephone"));
                final Button callButtonFinal = callButton;
                callButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + callButtonFinal.getText().toString()));
                            startActivity(callIntent);
                        } catch (ActivityNotFoundException activityException) {
                            Log.e("Calling a Phone Number", "Call failed", activityException);
                        }
                    }
                });
            }
            if(extras.getString("restaurantStreet") != null) {
                textStreet.setText("Straat: "+extras.getString("restaurantStreet"));
            }
            if(extras.getString("restaurantZipcode") != null) {
                textZipcode.setText("Postcode: " + extras.getString("restaurantZipcode"));
            }
            if(extras.getString("restaurantCity") != null) {
                textCity.setText("Stad: " + extras.getString("restaurantCity"));
            }

        }
    }
}
