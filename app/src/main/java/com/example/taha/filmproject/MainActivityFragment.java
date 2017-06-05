package com.example.taha.filmproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Movie> items;
    private MovieAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvHistorial = (ListView) view.findViewById(R.id.lvHistorial);


        items = new ArrayList<>();
        adapter = new MovieAdapter(
                getContext(),
                R.layout.datos_item,
                items
        );

        lvHistorial.setAdapter(adapter);

        lvHistorial.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Movie movies = (Movie) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getContext(), DetalleFilmActivity.class);
                intent.putExtra("movies", movies);
                startActivity(intent);
            }
        });




        return view;
    }


    public void onStart() {
        super.onStart();
        refresh();
    }



    private void refresh() {

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

    }



    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Movie>> {
        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            Api mlApi = new Api();
            ArrayList<Movie> result;
            result = mlApi.getDades();
            Log.d("DEBUG=****>", result != null ? result.toString() : null);

            return result;
        }
        @Override
        protected void onPostExecute(ArrayList<Movie> cartap) {
            super.onPostExecute(cartap);
            adapter.clear();
            for(int i = 0; i < cartap.size(); i++)
            {
                adapter.add(cartap.get(i));
            }

        }

    }













}
