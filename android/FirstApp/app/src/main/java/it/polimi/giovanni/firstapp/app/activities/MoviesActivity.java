package it.polimi.giovanni.firstapp.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.polimi.giovanni.firstapp.R;
import it.polimi.giovanni.firstapp.app.service.MovieService;
import it.polimi.giovanni.firstapp.app.adapters.MovieArrayAdapter;
import it.polimi.giovanni.firstapp.app.model.Movie;

public class MoviesActivity extends AppCompatActivity {

    private List<Movie> movies;
    private MovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieService = MovieService.getInstance(this);

        retrieveMovies();

    }

    private void retrieveMovies(){

        final MoviesActivity self = this;

        movieService.getAllMovies(new MovieService.Callback() {
            @Override
            public void onLoad(List<Movie> movies) {

                Collections.sort(movies, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        return m1.getTitle().compareTo(m2.getTitle());
                    }
                });

                self.movies = movies;
                setupListView();
            }

            @Override
            public void onFailure() {

            }
        });


    }

    private void setupListView(){

        ListView moviesListView = (ListView) findViewById(R.id.moviesList);


        MovieArrayAdapter moviesArrayAdapter = new MovieArrayAdapter(this, this.movies);
        moviesListView.setAdapter(moviesArrayAdapter);

        moviesListView.
                setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int i, long l) {
                        String movieTitle = movies.get(i).getTitle();
                        Toast toast = Toast.makeText(MoviesActivity.this,
                                "Movie "+movieTitle+" was clicked", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }

}
