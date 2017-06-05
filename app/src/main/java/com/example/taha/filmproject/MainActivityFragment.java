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
import android.databinding.DataBindingUtil;
import com.example.taha.filmproject.databinding.FragmentMainBinding;
import android.net.Uri;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;


import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Movie> items;
    //private MovieAdapter adapter;
    private MovieCursorAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View  view = inflater.inflate(R.layout.fragment_main, container, false);
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        //ListView lvHistorial = (ListView) view.findViewById(R.id.lvHistorial);


        items = new ArrayList<>();
        adapter = new MovieCursorAdapter(getContext(), Movie.class);
        /*adapter = new MovieAdapter(
                getContext(),
                R.layout.datos_item,
                items
        );*/

        binding.lvHistorial.setAdapter(adapter);

        binding.lvHistorial.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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



    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void  doInBackground(Void... voids) {
            Api mlApi = new Api();
            ArrayList<Movie> result;
            result = mlApi.getDades();
            Log.d("DEBUG=****>", result != null ? result.toString() : null);

            /*UriHelper helper = UriHelper.with(MovieContentProvider.AUTHORITY);
            Uri movieUri = helper.getUri(Movie.class);
            cupboard().withContext(getContext()).put(movieUri, Movie.class, result);*/
            DataManager.deleteMovie(getContext());
            DataManager.saveMovie(result, getContext());


            //return result;
            return null;
        }
        /*@Override
        protected void onPostExecute(ArrayList<Movie> cartap) {
            super.onPostExecute(cartap);
            adapter.clear();
            for(int i = 0; i < cartap.size(); i++)
            {
                adapter.add(cartap.get(i));
            }

        }*/

    }













}
