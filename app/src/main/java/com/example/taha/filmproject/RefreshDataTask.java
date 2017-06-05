package com.example.taha.filmproject;

/**
 * Created by taha on 06/06/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import com.alexvasilkov.events.Events;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;


public class RefreshDataTask extends AsyncTask<Void, Void, Void> {
    private Context context;

    RefreshDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Events.post("start-downloading-data");
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Api mlApi = new Api();
        ArrayList<Movie> result;
        result = mlApi.getDades();
        Log.d("DEBUG=****>", result != null ? result.toString() : null);

            /*UriHelper helper = UriHelper.with(MovieContentProvider.AUTHORITY);
            Uri movieUri = helper.getUri(Movie.class);
            cupboard().withContext(getContext()).put(movieUri, Movie.class, result);*/
            DataManager.deleteMovie(context);
            DataManager.saveMovie(result, context);


            //return result;
            return null;
        }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Events.post("finish-downloading-data");
    }



}
