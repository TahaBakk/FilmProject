package com.example.taha.filmproject;



import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.alexvasilkov.events.Events;
import com.example.taha.filmproject.databinding.FragmentMainBinding;




/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    //private ArrayList<Movie> items;
    //private MovieAdapter adapter;
    private MovieCursorAdapter adapter;
    private ProgressDialog dialog;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View  view = inflater.inflate(R.layout.fragment_main, container, false);
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        //ListView lvHistorial = (ListView) view.findViewById(R.id.lvHistorial);


        //items = new ArrayList<>();
        /*adapter = new MovieAdapter(
                getContext(),
                R.layout.datos_item,
                items
        );*/

        adapter = new MovieCursorAdapter(getContext(), Movie.class);

        binding.lvHistorial.setAdapter(adapter);

        binding.lvHistorial.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Movie movies = (Movie) adapterView.getItemAtPosition(i);

                if (!esTablet()) {
                Intent intent = new Intent(getContext(), DetalleFilmActivity.class);
                intent.putExtra("movies", movies);
                startActivity(intent);
                }else {
                    Events.create("cartas-selected").param(movies).post();
                }
            }
        });

        getLoaderManager().initLoader(0,null, this);

        return view;
    }

    /*@Events.Subscribe("start-downloading-data")
    void preRefresh() {
        dialog.show();
    }

    @Events.Subscribe("finish-downloading-data")
    void afterRefresh() {
        dialog.dismiss();
    }*/


    public void onStart() {
        super.onStart();
        refresh();
        //Events.register(this);
    }

    boolean esTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }


    private void refresh() {

        //RefreshDataTask task = new RefreshDataTask();
        RefreshDataTask task = new RefreshDataTask(getActivity().getApplicationContext());
        task.execute();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }


    /*private class RefreshDataTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void  doInBackground(Void... voids) {
            Api mlApi = new Api();
            ArrayList<Movie> result;
            result = mlApi.getDades();
            Log.d("DEBUG=****>", result != null ? result.toString() : null);

            /*UriHelper helper = UriHelper.with(MovieContentProvider.AUTHORITY);
            Uri movieUri = helper.getUri(Movie.class);
            cupboard().withContext(getContext()).put(movieUri, Movie.class, result);*/
            /*DataManager.deleteMovie(getContext());
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

    //}













}
