package com.example.taha.filmproject;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;
import android.support.v4.content.CursorLoader;
/**
 * Created by taha on 05/06/2017.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(MovieContentProvider.AUTHORITY);
    private static Uri MOVIE_URI = URI_HELPER.getUri(Movie.class);

    static void saveMovie(ArrayList<Movie> movie, Context context) {
        cupboard().withContext(context).put(MOVIE_URI, Movie.class, movie);
    }

    static void deleteMovie(Context context) {
        cupboard().withContext(context).delete(MOVIE_URI, "_id > ?", "0");
    }

    static CursorLoader getCursorLoader(Context context){
        return  new CursorLoader(context, MOVIE_URI, null, null, null, null);
        }

}
