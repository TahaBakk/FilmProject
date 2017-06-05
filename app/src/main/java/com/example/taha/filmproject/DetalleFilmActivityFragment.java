package com.example.taha.filmproject;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.alexvasilkov.events.Events;
import com.alexvasilkov.events.Event;

import com.bumptech.glide.Glide;
import com.example.taha.filmproject.databinding.FragmentDetalleFilmBinding;

//import com.example.x3727349s.visorcartasmagic.databinding.FragmentDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetalleFilmActivityFragment extends Fragment {

    /*private View view;
    private ImageView ivPosterImage;
    private TextView tvVote_average;
    private TextView title;
    private TextView popularity;
    private TextView poster_path;
    private TextView original_language;
    private TextView original_title;
    private TextView overview;
    private TextView release_date;*/
    private FragmentDetalleFilmBinding binding;


    public DetalleFilmActivityFragment() {
    }


    @Override
    public void onStart() {
        super.onStart();
        Events.register(this);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.fragment_detalle_film, container, false);
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_detalle_film, container, false);
        View view = binding.getRoot();

        Intent i = getActivity().getIntent();

        if (i != null){
            Movie movies = (Movie) i.getSerializableExtra("movies");
            System.out.println("asdasdasd"+movies);
            if (movies != null){
                updateui(movies);
            }
        }

        return view;
    }

    @Events.Subscribe("cartas-selected")
    private void onMovieSelected(Movie movies) {
        updateui(movies);
    }

    private void updateui(Movie movies) {
        Log.d("CARTA", movies.toString());

       /* ivPosterImage = (ImageView) view.findViewById(R.id.ivPosterImageList);
        tvVote_average = (TextView) view.findViewById(R.id.textView);
        title = (TextView) view.findViewById(R.id.tvTitulo);
        popularity = (TextView) view.findViewById(R.id.textView2);
        //poster_path = (TextView) view.findViewById(R.id.tvDescripcion);
        original_language = (TextView) view.findViewById(R.id.textView3);
        original_title = (TextView) view.findViewById(R.id.tvTituloOriginal);
        overview = (TextView) view.findViewById(R.id.tvoverview);

        tvVote_average.setText("Vote average: "+String.valueOf(movies.getVote_average()));
        title.setText(movies.getTitle());
        popularity.setText("Popularity: "+String.valueOf(movies.getPopularity()));
        original_language.setText("Original language: "+movies.getOriginal_language());
        original_title.setText("Original title: "+movies.getOriginal_title());
        overview.setText("Overview: "+movies.getOverview());
        String urlImagen = "https://image.tmdb.org/t/p/w500"+movies.getPoster_path();
        Glide.with(getContext()).load(urlImagen).into(ivPosterImage);*/


        binding.textView.setText("Vote average: "+String.valueOf(movies.getVote_average()));
        binding.tvTitulo.setText(movies.getTitle());
        binding.textView2.setText("Popularity: "+String.valueOf(movies.getPopularity()));
        binding.textView3.setText("Original language: "+movies.getOriginal_language());
        binding.tvTituloOriginal.setText("Original title: "+movies.getOriginal_title());
        binding.tvoverview.setText("Overview: "+movies.getOverview());
        String urlImagen = "https://image.tmdb.org/t/p/w500"+movies.getPoster_path();
        Glide.with(getContext()).load(urlImagen).into(binding.ivPosterImageList);



    }
}
