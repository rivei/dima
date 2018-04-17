package it.polimi.giovanni.firstapp.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import it.polimi.giovanni.firstapp.R;
import it.polimi.giovanni.firstapp.app.model.Movie;

/**
 * Created by giovanniquattrocchi on 30/11/17.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {


    private List<Movie> movies;
    private Context context;

    public MovieRecyclerAdapter(List<Movie> movies, Context context){
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View card = LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(card);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.vTitle.setText(movie.getTitle());
        holder.vDirector.setText(movie.getDirectorName());
        holder.vUserRating.setText(String.valueOf(movie.getUserRating()));
        Glide.with(context).load(movie.getPosterUrl()).centerCrop().crossFade().into(holder.vPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView vTitle;
        TextView vDirector;
        TextView vUserRating;
        ImageView vPoster;

        public MovieViewHolder(View view) {
            super(view);

            vTitle = (TextView) view.findViewById(R.id.movieTitle);
            vDirector = (TextView) view.findViewById(R.id.directorName);
            vUserRating = (TextView) view.findViewById(R.id.movie_user_rating);
            vPoster = (ImageView) view.findViewById(R.id.movie_poster_image);

        }
    }
}
