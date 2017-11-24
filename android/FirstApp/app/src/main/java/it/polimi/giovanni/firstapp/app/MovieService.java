package it.polimi.giovanni.firstapp.app;

import android.content.Context;
import android.content.res.TypedArray;

import it.polimi.giovanni.firstapp.R;
import it.polimi.giovanni.firstapp.app.data.MovieSQLiteRepository;
import it.polimi.giovanni.firstapp.app.model.Movie;
import it.polimi.giovanni.firstapp.app.rest.MovieRESTInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by giovanniquattrocchi on 23/11/17.
 */

public class MovieService {

    private MovieSQLiteRepository repository;
    private MovieRESTInterface restInterface;

    private static MovieService instance;

    private MovieService(Context context){
        repository = new MovieSQLiteRepository(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Commons.hostName)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restInterface = retrofit.create(MovieRESTInterface.class);

        fillWithDefault(context);
    }

    public static synchronized MovieService getInstance(Context context){
        if (instance == null)
            instance = new MovieService(context);

        return instance;
    }


    private void fillWithDefault(Context context){
        if (repository.findAll().getCount() > 0 )
            return;

        TypedArray movieIds = context.getResources().
                obtainTypedArray(R.array.movies_imdb_ids);
        TypedArray userRatings = context.getResources().
                obtainTypedArray(R.array.movies_user_ratings);
        TypedArray userReviews = context.getResources().
                obtainTypedArray(R.array.movies_user_reviews);

        for (int i = 0; i < movieIds.length(); i++){
            repository.add(new Movie(movieIds.getString(i),
                    userRatings.getFloat(i, 0.0f),
                    userReviews.getString(i)));
        }

    }

}
