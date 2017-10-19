package it.polimi.giovanni.firstapp.app.model;

/**
 * Created by giovanniquattrocchi on 19/10/17.
 */

public class Movie {

    private String title;
    private String directorName;

    public Movie(String title){
        this.title = title;
        this.directorName = "?";
    }

    public String getTitle(){
        return title;
    }

    @Override
    public String toString(){
        return "Title: "+title+" Director: "+directorName;
    }

}
