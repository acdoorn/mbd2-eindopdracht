package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

/**
 * Created by Alexander on 10/4/2015.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONParser extends AsyncTask<String, Void, String> {

    private MyListFragment.OnTaskCompleted listener;
    private RestaurantsFragment.OnTaskCompleted listener2;

    public JSONParser(MyListFragment.OnTaskCompleted listener) {
        this.listener=listener;
    }
    public JSONParser(RestaurantsFragment.OnTaskCompleted listener) {
        this.listener2=listener;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    @Override
    public String doInBackground(String... params) {
        try {
            String url = params[0];
            Integer timeout = 5000;
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            String jsonReply;
            if(c.getResponseCode()==201 || c.getResponseCode()==200)
            {
                System.out.println("Connection succeeded");
                InputStream response = c.getInputStream();
                jsonReply = convertStreamToString(response);

                return jsonReply;
            }

        } catch (MalformedURLException ex) {
//            Logger.getLogger(DebugServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
//            Logger.getLogger(DebugServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Failed";
    }
    @Override
    protected void onPostExecute(String jsonString) {
        if(listener != null) {
            listener.onTaskCompleted(jsonString);
        }
        else if(listener2 != null) {
            listener2.onTaskCompleted(jsonString);
        }
    }


}