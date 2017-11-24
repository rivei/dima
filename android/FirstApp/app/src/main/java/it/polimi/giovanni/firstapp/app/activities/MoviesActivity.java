package it.polimi.giovanni.firstapp.app.activities;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.polimi.giovanni.firstapp.R;
import it.polimi.giovanni.firstapp.app.Commons;
import it.polimi.giovanni.firstapp.app.MovieService;
import it.polimi.giovanni.firstapp.app.adapters.MovieArrayAdapter;
import it.polimi.giovanni.firstapp.app.data.MovieCursor;
import it.polimi.giovanni.firstapp.app.data.MovieDBContract;
import it.polimi.giovanni.firstapp.app.data.MovieSQLiteRepository;
import it.polimi.giovanni.firstapp.app.model.Movie;
import it.polimi.giovanni.firstapp.app.rest.MovieRESTInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {

    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movies);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)
                findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        MovieService service = MovieService.getInstance(this);

        retrieveMovies();

    }

    private void retrieveMovies(){

        final TypedArray movieIds = getResources().obtainTypedArray(R.array.movies_imdb_ids);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Commons.hostName)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieRESTInterface restInterface = retrofit.create(MovieRESTInterface.class);


        for (int i = 0; i < movieIds.length(); i++){

            Call<Movie> call = restInterface.getMovie(movieIds.getString(i), Commons.apiKey);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movie = response.body();
                    movies.add(movie);
                    if (movies.size() == movieIds.length()){
                        setupListView();
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });

        }


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
