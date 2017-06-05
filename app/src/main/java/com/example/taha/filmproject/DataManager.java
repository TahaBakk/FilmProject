package com.example.taha.filmproject;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;
/**
 * Created by taha on 05/06/2017.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(MovieContentProvider.AUTHORITY);
    private static Uri MOVIE_URI = URI_HELPER.getUri(Movie.class);

    static void saveMovie(ArrayList<Movie> cartas, Context context) {
        cupboard().withContext(context).put(MOVIE_URI, Movie.class, cartas);
    }

}