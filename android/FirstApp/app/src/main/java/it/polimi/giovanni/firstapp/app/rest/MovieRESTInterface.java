package it.polimi.giovanni.firstapp.app.rest;

import it.polimi.giovanni.firstapp.app.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by giovanniquattrocchi on 26/10/17.
 */

public interface MovieRESTInterface {

    @GET("/?")
    public Call<Movie> getMovie(@Query("i") String id,
                                @Query("apiKey") String apiKey);
}
