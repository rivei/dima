package it.polimi.giovanni.firstapp.app.activities;

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

import it.polimi.giovanni.firstapp.R;
import it.polimi.giovanni.firstapp.app.model.Movie;

public class MoviesActivity extends AppCompatActivity {

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

        //String[] movies = {"Kill Bill", "Interstellar", "Blue Velvet"};

        final Movie[] movies = {new Movie("Kill Bill"),
                new Movie("Interstellar"),
                new Movie("Blue Velvet")};

        ListView moviesListView = (ListView) findViewById(R.id.moviesList);

        ArrayAdapter<Movie> moviesArrayAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1 , movies);
        moviesListView.setAdapter(moviesArrayAdapter);

        moviesListView.
                setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {

                String movieTitle = movies[i].getTitle();
                Toast toast = Toast.makeText(MoviesActivity.this,
                        "Movie "+movieTitle+" was clicked", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

}
