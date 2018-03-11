package it.polimi.giovanni.firstapp.app.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import it.polimi.giovanni.firstapp.app.model.Movie;

import static it.polimi.giovanni.firstapp.app.data.MovieDBContract.*;

/**
 * Created by giovanniquattrocchi on 23/11/17.
 */

public class MovieSQLiteRepository {

    private SQLiteDatabase db;

    public MovieSQLiteRepository(Context context){
        db = getWritableDatabase(context);
    }

    public void add(Movie movie){
        db.execSQL("INSERT OR REPLACE INTO "+MovieEntry.TABLE_NAME+" ("+
                        MovieEntry._ID+","+
                        MovieEntry.USER_RATING_CLMN+","+
                        MovieEntry.USER_REVIEW_CLMN+")"+
                        "VALUES(?"+","+"?"+","+"?)",
                new Object[]{movie.getImdbId(), movie.getUserRating(),
                        movie.getUserReview()});
    }

    public MovieCursor findById(String id){
        String query = "SELECT * FROM "+ MovieEntry.TABLE_NAME
                + " WHERE "+ MovieEntry._ID +" = ?";

        Cursor cursor = db.rawQuery(query, new String[]{id});

        return new MovieCursor(cursor);
    }

    public MovieCursor findAll(){
        Cursor cursor = db.rawQuery("SELECT * FROM "+ MovieEntry.TABLE_NAME,
                null);
        return new MovieCursor(cursor);
    }

}
