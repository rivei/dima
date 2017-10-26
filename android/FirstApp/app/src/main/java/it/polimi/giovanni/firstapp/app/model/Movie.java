package it.polimi.giovanni.firstapp.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giovanniquattrocchi on 19/10/17.
 */

public class Movie {

    private String title;
    private String directorName;

    public Movie(String title, String directorName){
        this.title = title;
        this.directorName = directorName;
    }

    public String getTitle(){
        return title;
    }

    public String getDirectorName() {
        return directorName;
    }

    @Override
    public String toString(){
        return "Title: "+title+
                " Director: "+directorName;
    }

    public static List<Movie> getMovies(){

        List<Movie> movies = new ArrayList<Movie>();

        movies.add(new Movie("Mulholland Drive", "David Lynch"));
        movies.add(new Movie("Interstellar", "Christopher Nolan"));
        movies.add(new Movie("Kill Bill", "Quentin Tarantino"));
        movies.add(new Movie("The Texas Chain Saw Massacre", "Tobe Hooper"));
        movies.add(new Movie("Videodrome", "David Cronemberg"));
        movies.add(new Movie("My Neighbor Totoro", "Hayao Miyazaki"));
        movies.add(new Movie("Scream", "Wes Craven"));
        movies.add(new Movie("Vertigo", "Alfred Hitchcock"));
        movies.add(new Movie("No Country For Old Men", "Joel and Ethan Coen"));
        movies.add(new Movie("Carrie", "Brian De Palma"));
        movies.add(new Movie("Rosemary's Baby", "Roman Polanski"));


        return movies;

    }

}
