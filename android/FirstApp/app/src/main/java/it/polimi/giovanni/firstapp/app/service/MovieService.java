package it.polimi.giovanni.firstapp.app.service;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.MutableBoolean;

import java.util.ArrayList;
import java.util.List;

import it.polimi.giovanni.firstapp.R;
import it.polimi.giovanni.firstapp.app.Commons;
import it.polimi.giovanni.firstapp.app.data.MovieCursor;
import it.polimi.giovanni.firstapp.app.data.MovieSQLiteRepository;
import it.polimi.giovanni.firstapp.app.model.Movie;
import it.polimi.giovanni.firstapp.app.rest.MovieRESTInterface;
import retrofit2.Call;
import retrofit2.Response;
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

    public void getAllMovies(final Callback callback){

        MovieCursor movieCursor = repository.findAll();
        final int count = movieCursor.getCount();

        final List<Movie> res = new ArrayList<>();

        while(movieCursor.moveToNext()){

            String imdbId = movieCursor.getImdbId();
            final String userReview = movieCursor.getUserReview();
            final float userRating = movieCursor.getUserRating();

            final MutableBoolean alreadyFailed = new MutableBoolean(false);

            restInterface.getMovie(imdbId, Commons.apiKey).enqueue(new retrofit2.Callback<Movie>() {

                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movie = response.body();
                    movie.setUserRating(userRating);
                    movie.setUserReview(userReview);
                    res.add(movie);

                    if (res.size() == count){
                        callback.onLoad(res);
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    if (alreadyFailed.value == false){
                        alreadyFailed.value = true;
                        callback.onFailure();
                    }
                }
            });
        }

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

    public interface Callback {

        void onLoad(List<Movie> movies);
        void onFailure();

    }

}
