package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class MenuActivity extends ActionBarActivity {
    private TextView switchStatus;
    private Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Need to check if Activity has been switched to landscape mode
        // If yes, finished and go back to the start Activity
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            MenuFragment menuFragment = (MenuFragment) getFragmentManager()
                    .findFragmentById(R.id.menuFragment);
        }

        mySwitch = (Switch) findViewById(R.id.switch1);

        //set the switch to ON
        String checkValue;
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        checkValue = sharedPref.getString(getString(R.string.saved_sort),"rating");
        if(checkValue == "distance") {
            mySwitch.setChecked(true);
        }
        else {
            mySwitch.setChecked(false);
        }
        final SharedPreferences.Editor editor = sharedPref.edit();
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String value;
                if(isChecked){
                    value = "distance";
                }else{
                    value = "rating";
                }
                editor.putString(getString(R.string.saved_sort), value);
                editor.commit();

            }
        });

        //check the current state before we display the screen

        String value;
        if(mySwitch.isChecked()){
            value = "distance";
        }
        else {
            value = "rating";
        }
        editor.putString(getString(R.string.saved_sort), value);
        editor.commit();
    }
}
