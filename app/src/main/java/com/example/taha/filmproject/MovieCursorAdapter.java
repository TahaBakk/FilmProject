package com.example.taha.filmproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import android.databinding.DataBindingUtil;
import com.example.taha.filmproject.databinding.DatosItemBinding;

/**
 * Created by taha on 05/06/2017.
 */

public class MovieCursorAdapter extends CupboardCursorAdapter<Movie>{


    public MovieCursorAdapter(Context context, Class<Movie> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Movie model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        DatosItemBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.datos_item, parent, false);

        return binding.getRoot();
    }
    //lo que me mostrara en cada item
    @Override
    public void bindView(View view, Context context, Movie movie) {
        DatosItemBinding binding = DataBindingUtil.getBinding(view);
        binding.tv1.setText(movie.getTitle());
        if (movie.getAdult()){
            binding.tv2.setText("Contenido para +18");
        }else{
            binding.tv2.setText("Contenido para todo el publico");
        }
        String urlImagen = "https://image.tmdb.org/t/p/w500"+movie.getPoster_path();
        Glide.with(context).load(urlImagen).into(binding.ivPosterImageList);
    }


}
