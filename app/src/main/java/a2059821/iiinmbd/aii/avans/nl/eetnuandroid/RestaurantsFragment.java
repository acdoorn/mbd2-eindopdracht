package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RestaurantsFragment extends Fragment {

    private OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rsslist_restaurants,
                container, false);
        return view;
    }

    public interface OnItemSelectedListener {
        public void onRssItemSelected();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement RestaurantsFragment.OnItemSelectedListener");
        }
    }

    // May also be triggered from the Activity
    public void updateDetail() {
        listener.onRssItemSelected();
    }



}