package com.example.taha.filmproject;

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by taha on 05/06/2017.
 */

public class Api {

    //imagen https://image.tmdb.org/t/p/w500/--RUTAIMAGEN

    static ArrayList<Movie> getMatch() {
        Uri builtUri = Uri.parse("https://api.themoviedb.org/3/movie/top_rated?api_key=82f0ce609a809c8375dc4061e7526935&language=en-US&page=1")
                .buildUpon()
                .build();

        String url = builtUri.toString();
        return doCall(url);

    }

    private static ArrayList<Movie> doCall(String url) {
        String JsonResponse = null;
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            JsonResponse = HttpUtils.get(url);

            ArrayList<Movie> list = processJson(JsonResponse);
            movies.addAll(list);

            return movies;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Movie> processJson(String jsonResponse) {
        ArrayList<Movie> Movies = new ArrayList<>();

        try {
            JSONObject data = null;
            data = new JSONObject(jsonResponse);
            JSONArray jsonMatchList = data.getJSONArray("results");

            for (int i = 0; i < jsonMatchList.length(); i++) {

                JSONObject dato = jsonMatchList.getJSONObject(i);

                Movie movie = new Movie();

                movie.setVote_count(dato.getInt("vote_count"));
                movie.setVote_average(dato.getDouble("vote_average"));
                movie.setTitle(dato.getString("title"));
                movie.setPopularity(dato.getDouble("popularity"));
                movie.setPoster_path(dato.getString("poster_path"));
                movie.setOriginal_language(dato.getString("original_language"));
                movie.setOriginal_title(dato.getString("original_title"));
                movie.setBackdrop_path(dato.getString("backdrop_path"));
                movie.setAdult(dato.getBoolean("adult"));
                movie.setOverview(dato.getString("overview"));

                System.out.println("movie probando == "+ movie.toString());

                Movies.add(movie);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Movies;
    }






}
