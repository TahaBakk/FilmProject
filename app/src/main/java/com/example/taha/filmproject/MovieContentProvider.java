package com.example.taha.filmproject;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by taha on 05/06/2017.
 */

public class MovieContentProvider extends CupboardContentProvider {


    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Movie.class);
    }

    public MovieContentProvider() {
        super(AUTHORITY, 1);
    }

}
