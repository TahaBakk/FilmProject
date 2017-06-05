package com.example.taha.filmproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by taha on 05/06/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {


    public MovieAdapter(Context context, int resource , List<Movie> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Movie movie = getItem(position);
        Log.w("XXXX", movie.toString());

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.datos_item, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv1);
        TextView tvAdult = (TextView) convertView.findViewById(R.id.tv2);


        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        tvTitle.setText(movie.getTitle());
        if (movie.getAdult()){
            tvAdult.setText("Contenido para +18");
        }else{
            tvAdult.setText("Contenido para todo el publico");
        }
        //Glide.with(getContext()).load(movie.getImageUrl()).into(ivPosterImage);

        return convertView;
    }


}
