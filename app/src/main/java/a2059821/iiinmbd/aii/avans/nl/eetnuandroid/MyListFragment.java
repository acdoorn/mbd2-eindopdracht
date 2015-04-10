package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MyListFragment extends Fragment {

    private OnItemSelectedListener listener;
    private OnTaskCompleted listener2;
    private View view;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rsslist_overview,
                container, false);

        String url = "https://api.eet.nu/tags";
        new JSONParser(this.listener2).execute(url);
        return view;      //Starting the task. Pass an url as the parameter.
    }

    public interface OnItemSelectedListener {
        public void onRssItemSelected();
    }

    public interface OnTaskCompleted{
        void onTaskCompleted();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener && activity instanceof OnTaskCompleted) {
            listener = (OnItemSelectedListener) activity;
            listener2 = (OnTaskCompleted) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnItemSelectedListener OR MyListFragment.OnTaskCompleted");
        }
    }


    // May also be triggered from the Activity
    public void updateDetail() {
        listener.onRssItemSelected();
    }


    public void updateList(String jsonString) {

    }

}
