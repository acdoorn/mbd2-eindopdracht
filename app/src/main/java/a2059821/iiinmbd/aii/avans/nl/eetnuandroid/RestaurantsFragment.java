package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RestaurantsFragment extends Fragment {

    private OnItemSelectedListener listener;
    private OnTaskCompleted listener2;
    private View view;
    private String tag;
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rsslist_restaurants,
                container, false);
        String tag;
        // Old method of passing tag
//        if (savedInstanceState == null) {
//            if(this.getActivity().getIntent().getExtras() == null) {
//                tag= null;
//            } else {
//                tag= this.getActivity().getIntent().getExtras().getString("tag");
//            }
//        } else {
//            tag= (String) savedInstanceState.getSerializable("tag");
//        }

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        tag = sharedPref.getString(getString(R.string.saved_tag),"african");

        String url = "https://api.eet.nu/venues?tags="+tag;
        new JSONParser(this.listener2).execute(url);
        return view;
    }

    public interface OnItemSelectedListener {
        void onRssItemSelected(String selectedItem);
    }

    public interface OnTaskCompleted{
        void onTaskCompleted(String jsonString);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener && activity instanceof OnTaskCompleted) {
            listener = (OnItemSelectedListener) activity;
            listener2 = (OnTaskCompleted) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement RestaurantsFragment.OnItemSelectedListener OR RestaurantsFragment.OnTaskCompleted");
        }
    }

    public void setTag(String t) {
        this.tag = t;
        System.out.println("tag set");
    }


}